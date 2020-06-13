//import required libraries 
import React, { Component } from 'react';
import { Container, Header, Content, Footer, FooterTab, Button, Left, Body, Right, Title, Text, Card, CardItem } from 'native-base';
import Icon from "react-native-vector-icons/Ionicons";
import { FlatList } from 'react-native-gesture-handler';
import axios from 'axios'
import { Avatar } from 'react-native-elements';
import AutoHeightImage from 'react-native-auto-height-image'

export default class Home extends Component {
  constructor(props) {
    super(props);
    /* set default placeholder values that we will set our data to */
    this.state = {
      chits: [],
      doUpdate: false,
      location: [],
      sendLocation: ""
    }
  };

  componentDidMount() {
    // update feed and grab latest 10 posts
    this.state.doUpdate = true;
    this.componentDidUpdate();
  }

  async componentDidUpdate() {
    //send request to server for the 10 latest chits
    if (this.state.doUpdate == true) {
      axios.get("http://10.0.2.2:3333/api/v0.0.5/chits")
        .then(resp => {
          this.setState({ chits: resp.data });
        });   
      this.state.doUpdate = false;
    }
  }

  //map screen, we pass the location for the map through the navigation prop to be used on that screen
  goToMapview(data) {
    this.setState({ sendLocation: data })
    this.props.navigation.navigate('Map', {
      location: data
    })
  }
  //render UI, images, posts and buttons for user interaction
  render() {
    return (
      <Container>
        <Header>
          <Left />
          <Body>
            <Title>Chits</Title>
          </Body>
          <Right>
            <Button transparent onPress={() => this.setState({ doUpdate: true })}>
              <Icon
                name="md-refresh"
                color="#ccc"
                size={35}
              />
            </Button>
            <Button transparent onPress={() => this.props.navigation.navigate('Search')}>
              <Icon
                name="md-search"
                color="#ccc"
                size={35}
              />
            </Button>
          </Right>
        </Header>
        <FlatList
          //inverted   
          data={this.state.chits}
          initialNumToRender={this.state.chits.length}
          keyExtractor={(i) => i.toString()}
          renderItem={({ item }) =>
            <Card style={{
              borderRadius: 25,
              padding: 5,
            }}>
              <CardItem header>
                <Avatar
                  size="large"
                  rounded
                  containerStyle={{ alignSelf: "center", top: 25, bottom: 15 }}
                  source={{
                    uri: "http://10.0.2.2:3333/api/v0.0.5/user/" + item.user.user_id + "/photo"
                  }}
                />
                <Title style={{ color: '#000', fontSize: 15 }}>{`   @${item.user.given_name}`} </Title>
              </CardItem>
              <CardItem>
                <Text>{`${item.chit_content}`}</Text>
              </CardItem>
              <AutoHeightImage
                width={300}
                style={{ alignSelf: "center", top: 25, bottom: 15 }}
                source={{
                  uri: "http://10.0.2.2:3333/api/v0.0.5/chits/" + item.chit_id + "/photo"
                }}
              />
              <CardItem footer>
                <Text>{`${new Date(item.timestamp).toLocaleTimeString()} - ${new Date(item.timestamp).toLocaleDateString()}`}</Text>
                <Button transparent onPress={() => this.goToMapview(item.location)}>
                  <Text>Location</Text>
                </Button>
              </CardItem>
            </Card>
          }
        />
        <Content />
        <Footer>
          <FooterTab>
            <Button onPress={() => this.props.navigation.navigate('Settings')}>
              <Icon
                name="ios-construct"
                color="#ccc"
                size={35}
              />
            </Button>
            <Button
              onPress={() => this.props.navigation.navigate('Compose')}>
              <Icon
                name="ios-create"
                color="#ccc"
                size={35}
              />
            </Button>
          </FooterTab>
        </Footer>
      </Container >
    );
  }
}