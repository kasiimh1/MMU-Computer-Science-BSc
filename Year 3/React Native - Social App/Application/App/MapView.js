//import required libraries 
import React, { Component } from 'react';
import 'react-native-gesture-handler';
import { StyleSheet, Text } from 'react-native';
import MapView from 'react-native-maps'

export default class Map extends Component {
    constructor(props) {
        super(props);
        this.state = {
            /* set default placeholder values in case no value is returned 
               from location (ie user has maps disabled) otherwise we error out.
            */
            hasLocation: false,
            region: {
                latitude: 0,
                longitude: 0,
                latitudeDelta: 0.0,
                longitudeDelta: 0.0
            },
        }
    };
    //this function runs at load of this page
    componentDidMount() {
        //grab the received params that have been sent when moving to this page
        const { navigation } = this.props
        const location = navigation.getParam('location', '')

        if (location != null) {
            //set state with updates values
            this.state.region.longitude = location.longitude
            this.state.region.latitude = location.latitude
            //check that we actually have the values
            if (this.state.region.latitude != 0 && this.state.region.longitude != 0) {
                this.setState({ hasLocation: true })
            }
        }
    }

    render() {
        if (this.state.hasLocation) {
            return (
                //render our map on screen
                <MapView
                    style={styles.map}
                    region={this.state.region}
                />
            );
            //if we don't have a location for that message display text below
        } if (!this.state.hasLocation) {
            return (<Text style={{
                top: 250,
                alignSelf: 'center',
                justifyContent: 'center',
            }
            }>No Location Provided, Press Back to Return Home!</Text>)
        }
    }
}

//map styling
const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
        position: 'absolute'
    },
    map: {
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
        position: 'absolute'
    }
});