//import required libraries 
import React, { Component } from 'react';
import { View, Button, Title, Text, Card, CardItem } from 'native-base';
import 'react-native-gesture-handler';
import axios from 'axios';
import { SearchBar, Avatar } from 'react-native-elements';
import { FlatList } from 'react-native-gesture-handler';
import { accountPhoto, checkLocalStorage } from '../HelperUtil'
import Icon from "react-native-vector-icons/Ionicons";

export default class Search extends Component {
    constructor(props) {
        super(props);
        /* set default placeholder values that we will set our data to */
        this.state = {
            followers: [],
            following: [],
            foundUser: [],
            search: "",
            profileImage: "",
            userid: "",
            useremail: "",
            username: "",
            name: "",
            followersNames: "",
            followingNames: "",
            token: ""
        }
    };

    //find the search user and return their data and store in state, also grab their, followers and following users and avatar photo
    findUser = async (query) => {
        const token = await checkLocalStorage("@local_token");
        this.setState({ token: token })
        this.setState({ followersNames: "" })
        this.setState({ followingNames: "" })
        this.setState({ foundUser: null })
        axios.get("http://10.0.2.2:3333/api/v0.0.5/search_user?q=" + query)
            .then(resp => {
                this.setState({ foundUser: resp.data });
                this.state.foundUser.map(({ user_id, given_name, family_name, email }) => {
                    this.setState({ userid: user_id })
                    this.setState({ username: given_name })
                    this.setState({ name: family_name })
                    this.setState({ useremail: email })
                })
                this.getFollowers(this.state.userid)
                this.getFollowing(this.state.userid)
                accountPhoto(this.state.userid).then(resp => {
                    this.setState({ profileImage: resp })
                })
            });
    }
    //get followers 
    getFollowers = (id) => {
        axios.get("http://10.0.2.2:3333/api/v0.0.5/user/" + id + '/followers')
            .then(resp => {
                this.setState({ followers: resp.data });
                this.state.followers.map(({ given_name }) => {
                    this.state.followersNames += given_name + " , "
                })
                this.setState({ followersNames: this.state.followersNames.toString() })
            });
    }
    //get following
    getFollowing = (id) => {
        axios.get("http://10.0.2.2:3333/api/v0.0.5/user/" + id + '/following')
            .then(resp => {
                this.setState({ following: resp.data });
                this.state.following.map(({ given_name }) => {
                    this.state.followingNames += given_name + " , "
                })
                this.setState({ followingNames: this.state.followingNames.toString() })
            });
    }
    //send request to server to follow user
    doFollow = () => {
        axios.post("http://10.0.2.2:3333/api/v0.0.5/user/" + this.state.userid + "/follow", {}, {
            headers: {
                'Content-Type': 'application/json',
                'X-Authorization': this.state.token
            }
        })
            .then(() => {
                alert("Follow Completed!")
            })
            .catch(() => {
                alert("Already Following User!")
            })
    }
    //send request to server to unfollow user
    doUnfollow = () => {
        axios.delete("http://10.0.2.2:3333/api/v0.0.5/user/" + this.state.userid + "/follow", {
            headers: {
                'Content-Type': 'application/json',
                'X-Authorization': this.state.token
            }
        })
            .then(() => {
                alert("Unfollow Completed!")
            })
            .catch(() => {
                alert("Not Following User!")
            })
    }
  //render searched users details, avatar and buttons to follow and unfollow
    render() {
        return (
            <View>
                <SearchBar
                    placeholder="Search For User..."
                    focus
                    value={this.state.search}
                    onChangeText={(search) => this.setState({ search })}
                />
                <Button onPress={() => this.findUser(this.state.search)} style={{
                    top: 25,
                    width: 150,
                    alignSelf: 'center',
                    borderRadius: 10,
                    justifyContent: 'center',
                }}><Text>Search!</Text>
                </Button>
                <View>
                    <FlatList
                        style={{ top: 25 }}
                        data={this.state.foundUser}
                        keyExtractor={(x, i) => i.toString()}
                        renderItem={({ item }) =>
                            <Card style={{
                                borderRadius: 25,
                                flex: 1
                            }}>
                                <CardItem header>
                                    <Avatar
                                        size="large"
                                        rounded
                                        activeOpacity={0.7}
                                        containerStyle={{ alignSelf: "center", top: 0 }}
                                        source={{
                                            uri: this.state.profileImage
                                        }}
                                    />
                                    <Title style={{ color: '#000', fontSize: 15 }}>{`   @${item.given_name}`} </Title>
                                    <Button style={{ backgroundColor: 'green', width: 45, marginRight: 5, justifyContent: 'center', alignItems: 'center' }} onPress={() => this.doFollow()}>
                                        <Icon
                                            name="user-follow"
                                            color="#ccc"
                                            size={25}
                                        />
                                    </Button>
                                    <Button style={{ backgroundColor: 'red', width: 45, justifyContent: 'center', alignItems: 'center' }} onPress={() => this.doUnfollow()}>
                                        <Icon
                                            name="md-remove"
                                            color="#ccc"
                                            size={25}
                                        />
                                    </Button>
                                </CardItem>
                                <CardItem>
                                    <Text>Name: {`${item.family_name}`}</Text>
                                </CardItem>
                                <CardItem>
                                    <Text>Email: {`${item.email}`}</Text>
                                </CardItem>
                                <CardItem>
                                    <Text>Followers: {`${this.state.followersNames}`}</Text>
                                </CardItem>
                                <CardItem>
                                    <Text>Following: {`${this.state.followingNames}`}</Text>
                                </CardItem>
                            </Card>
                        }
                    />
                </View>
            </View >
        );
    }
}