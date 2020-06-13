//import required libraries 
import React, { Component } from 'react';
import { View, Spinner } from 'native-base';
import 'react-native-gesture-handler';
import { checkLocalStorage } from './HelperUtil'

export default class SplashScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
      //set authenticated to false by default, incase user login creds don't exist
      isAuthenticated: false,
    }
  }
  //this function runs at load of this page
  async componentDidMount() {
    /* call async method to return a promise that this action will be completed, 
       wait for response from localStorage with user token and id. Redirect to Login if not found
       otherwise send user to homescreen
    */
    const token = await checkLocalStorage("@local_token");
    const id = await checkLocalStorage("@local_user_id");
    //this check reads token and id to check they're not null and the token is the present and required length
    if (token != null && id != null && token.length == 32) {
      //display home screen
      this.props.navigation.navigate('Home')
      console.log("Authenticated")
    }
    else {
      //display login screen
      this.props.navigation.navigate('Login')
      console.log("Not Authenticated")
    }
  }

  render() {
    return (
      //show user a loading icon while this class's code runs
      <View>
        <Spinner color="#0000ff" />
      </View>
    );
  }
}