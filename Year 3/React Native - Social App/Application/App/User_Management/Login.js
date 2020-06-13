//import required libraries 
import React, { Component } from 'react';
import { Header, Button, Left, Body, Right, Title, View, Form, Item, Text } from 'native-base';
import 'react-native-gesture-handler';
import axios from 'axios';
import { TextInput } from 'react-native'
import AsyncStorage from '@react-native-community/async-storage';

export default class Login extends Component {
  constructor(props) {
    super(props);
    /* set default placeholder values that we will set our data to */
    this.state = {
      email: '',
      password: '',
      authenticated: false,
      token: '',
      id: null,
      chittr: [],
    }
  };

  doLogin = () => {
    /* clear if not already authed */
    AsyncStorage.clear()
    axios.post("http://10.0.2.2:3333/api/v0.0.5/login", {
      "email": this.state.email,
      "password": this.state.password
    },
      { headers: { 'Content-Type': 'application/json' } })
      .then(resp => {
        this.setState({ chittr: resp.data });
        //return home if authenticated so user can continue using the application
        this.state.authenticated = true;
        this.props.navigation.navigate('Home')
        //debug messages
        console.log("[AUTH] Token received was: ", this.state.chittr.token)
        console.log("[AUTH] ID received was: ", this.state.chittr.id)
        //store the token and user id in local storage to be able to access at anytime
        AsyncStorage.setItem('@local_user_id', this.state.chittr.id.toString())
        AsyncStorage.setItem('@local_token', this.state.chittr.token.toString())
      })
      .catch(function (error) {
        //alert the user something went with logging in
        alert("ERROR", "Username or Password was Incorrect \nPlease Try Again!")
        console.log(error);
      });
  }

  /*render UI for entering username and password, render signup button incase user doesn't have an account already
    login calls the function above to authenticated the user 
  */
  render() {
    return (
      <View>
        <Header>
          <Left />
          <Body>
            <Title>Chittr Login</Title>
          </Body>
          <Right />
        </Header>
        <Form style={{
          top: 75
        }}>
          <Item>
            <TextInput
              placeholder="Email"
              keyboardType="email-address"
              onChangeText={(email) => this.setState({ email })}
              value={this.state.email} />
          </Item>
          <Item last>
            <TextInput
              placeholder="Password"
              secureTextEntry={true}
              onChangeText={(password) => this.setState({ password })}
              value={this.state.password} />
          </Item>
        </Form>
        <Button onPress={() => this.doLogin()} style={{
          top: 150,
          width: 150,
          alignSelf: 'center',
          borderRadius: 10,
          justifyContent: 'center'
        }}><Text>Login</Text></Button>
        <Button onPress={() => this.props.navigation.navigate("Signup")} style={{
          top: 180,
          width: 150,
          alignSelf: 'center',
          borderRadius: 10,
          justifyContent: 'center'
        }}><Text>Signup</Text></Button>
      </View>
    );
  }
}
