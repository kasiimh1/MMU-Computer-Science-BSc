//import required libraries 
import React, { Component } from 'react';
import { Header, Left, Body, Right, Title, View, Button, Text, StyleProvider } from 'native-base';
import 'react-native-gesture-handler';
import AsyncStorage from '@react-native-community/async-storage';
import { Avatar } from 'react-native-elements';
import { checkLocalStorage, accountPhoto, imgPickerProfileImage } from '../HelperUtil'
import axios from 'axios';

export default class Settings extends Component {
  constructor(props) {
    super(props);
    /* set default placeholder values that we will set our data to */
    this.state = {
      accountImage: "",
    }
  };

  //navigate to the login page
  Login() {
    this.props.navigation.navigate("Login")
  }

  // perform logout, send request to server, if successful wipe localstorage and navigate back to home screen and alert the user
  async Logout() {
    let token = await checkLocalStorage("@local_token");
    console.log(token)
    axios.post('http://10.0.2.2:3333/api/v0.0.5/logout', {}, {
      headers: {
        'Content-Type': 'application/json',
        'X-Authorization': token
      }
    }).then(() => {
      AsyncStorage.clear()
      this.props.navigation.navigate('Login')
      alert("You haven been signed out successfully")
    })
  }

  //fetch user_id from localstorage when loading page
  async componentDidMount() {
    let id = await checkLocalStorage("@local_user_id");
    accountPhoto(id).then(res => {
      this.setState({ accountImage: res })
    })
  }

  //render UI, avatars and buttons 
  render() {
    return (
      <View style={{}}>
        <Header>
          <Left />
          <Body>
            <Title>Chittr Settings</Title>
          </Body>
          <Right />
        </Header>
        <Avatar
          size="xlarge"
          rounded
          containerStyle={{ alignSelf: "center", top: 25 }}
          source={{
            uri: this.state.accountImage,
          }}
        />
        <Button onPress={() => imgPickerProfileImage()} style={{
          top: 50,
          width: 150,
          alignSelf: 'center',
          borderRadius: 10,
          justifyContent: 'center',
        }}><Text>Edit Avatar</Text>
        </Button>
        <Button onPress={() => this.Login()} style={{
          top: 75,
          width: 150,
          alignSelf: 'center',
          borderRadius: 10,
          justifyContent: 'center',
        }}><Text>Login</Text>
        </Button>
        <Button onPress={() => this.props.navigation.navigate('Account')} style={{
          top: 100,
          width: 150,
          alignSelf: 'center',
          borderRadius: 10,
          justifyContent: 'center',
        }}><Text>Edit Account</Text>
        </Button>
        <Button onPress={() => this.Logout()} style={{
          top: 125,
          width: 150,
          alignSelf: 'center',
          borderRadius: 10,
          justifyContent: 'center',
        }}><Text>Logout!</Text>
        </Button>
        <Text style={{
          top: 175,
          alignSelf: 'center',
          justifyContent: 'center',
        }}>Created by Kasim Hussain</Text>
      </View>
    );
  }
}