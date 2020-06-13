//import required libraries 
import React, { Component } from 'react';
import 'react-native-gesture-handler';
import AppNavigator from './App/AppNavigator';
import { mapping, dark as darkTheme } from '@eva-design/eva';
import { ApplicationProvider } from '@ui-kitten/components';

export default class App extends React.Component {
  render() {
    console.disableYellowBox = true;
    return (
      <ApplicationProvider mapping={mapping} theme={darkTheme}>
      <AppNavigator/>
      </ApplicationProvider>     
    );
  }
}
