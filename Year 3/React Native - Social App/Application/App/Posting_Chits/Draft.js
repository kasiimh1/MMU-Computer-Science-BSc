//import required libraries 
import React, { Component } from 'react';
import { View, Text, Button, Card, CardItem } from 'native-base';
import 'react-native-gesture-handler';
import { checkLocalStorage } from '../HelperUtil'
import axios from 'axios';
import AsyncStorage from '@react-native-community/async-storage';

export default class DraftScreen extends Component {
    constructor(props) {
        super(props);
        /* set default placeholder values that we will set our data to */
        this.state = {
            draftMessage: [],
        }
    }

    async componentDidMount() {
        //get user creds upon loading of this page
        const token = await checkLocalStorage("@local_token");
        this.setState({ token: token })
        let drafts = await checkLocalStorage("@draftMessages")
        this.setState({ draftMessage: JSON.parse(drafts) })
    }
    //send the draft message to the server
    sendChit = async () => {
        axios.post('http://10.0.2.2:3333/api/v0.0.5/chits', this.state.draftMessage
            , {
                headers: {
                    'Content-Type': 'application/json',
                    'X-Authorization': this.state.token
                }
            }).then(() => {
                alert("Draft Post sent successfully")
                this.props.navigation.navigate("Home")
                AsyncStorage.removeItem("@draftMessages")
            })
            .catch(error => {
                console.log(error);
                alert("Draft Post couldn't be sent to the server successfully")
                this.props.navigation.navigate("Home")
            });
    }
  //render UI, to display the draft message
    render() {
        if (this.state.draftMessage) {
            return (
                <View style={{}}>
                    <Card style={{
                        borderRadius: 25,
                        padding: 5,
                    }}>
                        <CardItem>
                            <Text>Message: {this.state.draftMessage.chit_content}</Text>
                        </CardItem>
                        <CardItem>
                            <Text>{`${new Date(this.state.draftMessage.timestamp).toLocaleTimeString()} - ${new Date(this.state.draftMessage.timestamp).toLocaleDateString()}`}</Text>
                        </CardItem>
                    </Card>
                    <Button onPress={() => this.sendChit()} style={{
                        top: 100,
                        width: 150,
                        alignSelf: 'center',
                        borderRadius: 10,
                        justifyContent: 'center',
                    }}><Text>Post Draft</Text></Button>
                </View>
            );
        }
        else {
            return (
                <View>
                    <Card style={{
                        borderRadius: 25,
                        padding: 5,
                    }}>
                        <CardItem>
                            <Text>No draft messages found</Text>
                        </CardItem>
                    </Card>
                </View>
            )
        }
    }
}