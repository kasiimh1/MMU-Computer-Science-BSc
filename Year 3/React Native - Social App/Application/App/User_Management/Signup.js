//import required libraries 
import React, { Component } from 'react';
import { Header, Button, Left, Body, Right, Title, View, Form, Item, Text } from 'native-base';
import 'react-native-gesture-handler';
import axios from 'axios';
import { TextInput } from 'react-native'

export default class Signup extends Component {
    constructor(props) {
        super(props);
        /* set default placeholder values that we will set our data to */
        this.state = {
            email: '',
            password: '',
            username: '',
            name: '',
            authenticated: false,
            token: '',
            id: null,
            chittr: [],
        }
    };
        //send new users creds to server to be processed
        doSignup = () => {
            axios.post("http://10.0.2.2:3333/api/v0.0.5/user", {
                "email": this.state.email,
                "password": this.state.password,
                "family_name": this.state.name,
                "given_name": this.state.username,
            },
                { headers: { 'Content-Type': 'application/json' } })
                .then(resp => {
                    //this.setState({ chittr: resp.data });
                    alert("Success!", "Please Login to Contiune")
                    //navigate back home if response is sucessful
                    this.props.navigation.navigate('Login')
                })
                .catch(function (error) {
                    alert("Something went wrong, please try again!")
                    console.log(error);
                });
            //debug 
            console.log(this.chittr)
        }
  //render UI, avatars and buttons 
    render() {
        return (
            <View style={{}}>
                <Header>
                    <Left />
                    <Body>
                        <Title>Chittr Signup</Title>
                    </Body>
                    <Right />
                </Header>
                <Form style={{
                    top: 75,
                }}>
                    <Item>
                        <TextInput
                            placeholder="Email"
                            keyboardType="email-address"
                            onChangeText={(email) => this.setState({ email })}
                            value={this.state.email}

                        />
                    </Item>
                    <Item last>
                        <TextInput
                            placeholder="Username"
                            onChangeText={(username) => this.setState({ username })}
                            value={this.state.username}
                        />
                    </Item>
                    <Item last>
                        <TextInput
                            placeholder="Name"
                            onChangeText={(name) => this.setState({ name })}
                            value={this.state.name}
                        />
                    </Item>
                    <Item last>
                        <TextInput
                            placeholder="Password"
                            secureTextEntry={true}
                            onChangeText={(password) => this.setState({ password })}
                            value={this.state.password}

                        />
                    </Item>
                </Form>
                <Button onPress={() => this.doSignup()} style={{
                    top: 250,
                    width: 150,
                    alignSelf: 'center',
                    borderRadius: 10,
                    justifyContent: 'center',
                }}><Text>Signup</Text></Button>
            </View>
        );
    }
}
