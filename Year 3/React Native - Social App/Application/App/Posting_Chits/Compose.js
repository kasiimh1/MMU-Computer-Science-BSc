//import required libraries 
import React, { Component } from 'react';
import { Header, Button, Left, Body, Right, Title, View, Form, Item, Text, Alert } from 'native-base';
import 'react-native-gesture-handler';
import { TextInput, PermissionsAndroid, } from 'react-native'
import axios from 'axios';
import { checkLocalStorage, imgPickerChitImage } from '../HelperUtil'
import Geolocation from 'react-native-geolocation-service';
import AsyncStorage from '@react-native-community/async-storage';

export default class Compose extends Component {
  constructor(props) {
    super(props);
    /* set default placeholder values that we will set our data to */
    this.state = {
      token: "",
      chit_id: 0,
      timestamp: 0,
      chit_content: "",
      longitude: 0,
      latitude: 0,
      user_id: 0,
      given_name: "",
      family_name: "",
      email: "",
      setLocation: false,
      setImage: false,
      isAuthenticated: true,
      location: null,
      locationPermission: false,
      draft: null
    }
  };

  //call location function
  findCoordinates = () => {
    //check if user has already given persmission.
    if (!this.state.locationPermission) {
      this.state.locationPermission = this.requestLocationPermission();
    }

    //get the current location and set in state
    Geolocation.getCurrentPosition((position) => {
      this.setState({ latitude: position.coords.latitude });
      this.setState({ longitude: position.coords.longitude });
    },
      (error) => { alert(error.message) },
      { enableHighAccuracy: true, timeout: 20000, maximumAge: 1000 });
  };
  //request permissions via system request to user
  async requestLocationPermission() {
    try {
      const granted = await PermissionsAndroid.request(PermissionsAndroid.PERMISSIONS.ACCESS_FINE_LOCATION,
        { title: 'Location Permission', message: 'This app requires access to your location.', buttonNeutral: 'Ask Me Later', buttonNegative: 'Cancel', buttonPositive: 'OK', });
      if (granted === PermissionsAndroid.RESULTS.GRANTED) {
        console.log('You can access location');
        return true;
      } else {
        console.log('Location permission denied');
        return false;
      }
    } catch (err) {
      console.warn(err);
    }
  }

  async componentDidMount() {
    //get current location upon loading of the screen
    this.findCoordinates();
    //fetch creds from localstorage 
    const token = await checkLocalStorage("@local_token");
    const user_id = await checkLocalStorage("@local_user_id");
    // set creds in state
    this.setState({ user_id: user_id })
    this.setState({ token: token })
    //check they're not null and token is length of 32
    if (this.state.token != null && this.state.user_id != null && this.state.token.length == 32) {
      this.state.isAuthenticated = true
    }
    //otherwise display alert to user, message couldn't be sent!
    if (!this.state.isAuthenticated) {
      alert("An error occured please try again!")
    }
  }

  //send message to server if already authenticated, wait on a promise before doing anything else
  sendChit = async () => {
    if (!this.state.isAuthenticated) {
      console.log("Error, not authenticated")
    }
    else {
      axios.post('http://10.0.2.2:3333/api/v0.0.5/chits', {
        "chit_id": this.state.chit_id,
        "timestamp": Date.parse(new Date()),
        "chit_content": this.state.chit_content,
        "location": {
          "longitude": this.state.longitude,
          "latitude": this.state.latitude
        },
        "user": {
          "user_id": 0,
          "given_name": this.state.given_name,
          "family_name": this.state.family_name,
          "email": this.state.email
        }
      }, {
        headers: {
          'Content-Type': 'application/json',
          'X-Authorization': this.state.token
        }
      }).then(resp => {
        console.log("Chit publised with id: " + resp.data.chit_id)
        //call image picker to allow for both browsing of a photo or taking a photo
        imgPickerChitImage(resp.data.chit_id)
        this.props.navigation.navigate("Home")
      })
        .catch(error => {
          console.log(error);
          alert("Post couldn't be sent to the server successfully")
        });
    }
  }

  // save the daraft message to localstorage 
  saveChit = () => {
    this.state.draft = {
      "chit_id": this.state.chit_id,
      "timestamp": Date.parse(new Date()),
      "chit_content": this.state.chit_content,
      "location": {
        "longitude": this.state.longitude,
        "latitude": this.state.latitude
      },
      "user": {
        "user_id": 0,
        "given_name": this.state.given_name,
        "family_name": this.state.family_name,
        "email": this.state.email
      }
    }
    //set drafts into localstorage stringify object 
    let draftmess = JSON.stringify(this.state.draft);
    AsyncStorage.setItem('@draftMessages', draftmess).then(() => {
      alert("Saved draft!")
    })
      //alert user if an error occured saving to drafts
      .catch(() => {
        alert("Error occured saving draft")
      })
    //return back to home screen
    this.props.navigation.navigate("Home")
  }
  //render UI, text input to enter data into and buttons to send request to the server 
  render() {
    return (
      <View style={{}}>
        <Header>
          <Left />
          <Body>
            <Title>Chittr Compose</Title>
          </Body>
          <Right />
        </Header>
        <Form style={{
          top: 75,
        }}>
          <Item>
            <Text style={{ position: 'absolute', top: 150, left: 200, right: 0, bottom: 0, justifyContent: 'center', alignItems: 'center' }}>Characters Left: {this.state.chit_content.length}/141</Text>
            <TextInput
              numberOfLines={10}
              multiline={true}
              maxLength={141}
              placeholder="Message"
              onChangeText={(chit_content) => this.setState({ chit_content })}
              value={this.state.chit_content}
            />
          </Item>
        </Form>
        <Button onPress={() => this.sendChit()} style={{
          top: 100,
          width: 150,
          alignSelf: 'center',
          borderRadius: 10,
          justifyContent: 'center',
        }}><Text>Post Message</Text></Button>
        <Button onPress={() => this.saveChit()} style={{
          top: 125,
          width: 150,
          alignSelf: 'center',
          borderRadius: 10,
          justifyContent: 'center',
        }}><Text>Save as Draft</Text></Button>
        <Button onPress={() => this.props.navigation.navigate("Draft")} style={{
          top: 150,
          width: 150,
          alignSelf: 'center',
          borderRadius: 10,
          justifyContent: 'center',
        }}><Text>View Drafts</Text></Button>
      </View>
    );
  }
}