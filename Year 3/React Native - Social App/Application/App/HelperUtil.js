//import required libraries 
import AsyncStorage from '@react-native-community/async-storage';
import axios from 'axios';
import { encode as btoa } from 'base-64'
import ImagePicker from 'react-native-image-picker';

/* Local storage helper function, runs as async waiting on the 
   reply/promise to be returned. When value is returned we return it with the received value
 */
export const checkLocalStorage = async (value) => {
  let request;
  await AsyncStorage.getItem(value).then(resp => {
    request = resp
  })
  return request;
}

/* axios request helper function, this was used to check the request and response of 
  our http requests and see where an error would occur and why. Very useful for debugging
  requests as needed
*/
export const axiosDebug = () => {
  axios.interceptors.request.use(function (config) {
    console.log(config)
    return config;
  }, function (error) {
    return Promise.reject(error);
  });
}

/* returns an account avatar if found on the server, function requires the user_id of the avatar
   to be passes into the function. This method waits for a reply again using async, we decode the
   image to base64 string and return the value to the caller. If no image is found the server returns
   the default image. An image will always be returned.s
*/
export const accountPhoto = async (id) => {
  let response;
  await axios.get('http://10.0.2.2:3333/api/v0.0.5/user/' + id + '/photo', { responseType: 'arraybuffer' })
    .then(resp => {
      const base64 = btoa(
        new Uint8Array(resp.data).reduce(
          (data, byte) => data + String.fromCharCode(byte),
          '',
        ),
      );
      response = "data:;base64," + base64;
      console.log("Got profile picture response from server")
    })
    .catch(
      console.log("No profile image found, using default img")
    );
  return response;
}

/* send avatar photo to the api, waiting on the token to be fetched from localstorage otherwise request
   will fail if no token is provided. headers need to be set correctly here otherwise server will reject the request!
*/
export const sendProfileImage = async (data) => {
  const token = await checkLocalStorage("@local_token");
  fetch('http://10.0.2.2:3333/api/v0.0.5/user/photo', {
    method: 'POST',
    headers: {
      'Content-Type': 'image/jpeg',
      'X-Authorization': token
    },
    body: data
  })
    .catch(error => {
      console.log(error);
      alert("Profile Image couldn't be set couldn't be sent to the server successfully")
    });
}
/* send chit message photo to the api, waiting on the token to be fetched from localstorage otherwise request
   will fail if no token is provided. headers need to be set correctly here otherwise server will reject the request!
*/
export const postChitImage = async (data, chitid) => {
  const token = await checkLocalStorage("@local_token");
  fetch('http://10.0.2.2:3333/api/v0.0.5/chits/' + chitid + '/photo', {
    method: 'POST',
    headers: {
      'Content-Type': 'image/jpeg',
      'X-Authorization': token
    },
    body: data
  })
    .catch(error => {
      console.log(error);
      alert("Chit Image couldn't be set couldn't be sent to the server successfully")
    });
}

export const imgPickerChitImage = (chitid) => {
  const options = {
    title: 'Select Chit Image',
    storageOptions: {
      skipBackup: true,
      path: 'images',
    },
  };

  ImagePicker.showImagePicker(options, (response) => {
    if (response.didCancel) {
      alert("Avatar update cancelled")
    } else if (response.error) {
      alert("Unknown error occured")
    } else {
      postChitImage(response, chitid).then(() => {
        response.didCancel
        //alert("Profile picture successfully updated")
        this.props.navigation.navigate('Home')
      })
    }
  });
}

/* image picker function that allows both selecting of an image from library and taking a photo with the built-in camera 
    on the device, convert to base64 and send to server 
*/
export const imgPickerProfileImage = () => {
  const options = {
    title: 'Select Avatar',
    storageOptions: {
      skipBackup: true,
      path: 'images',
    },
  };

  ImagePicker.showImagePicker(options, (response) => {
    if (response.didCancel) {
      alert("Avatar update cancelled")
    } else if (response.error) {
      alert("Unknown error occured")
    } else {
      sendProfileImage(response).then(() => {
        response.didCancel
        alert("Profile picture successfully updated")
        this.props.navigation.navigate('Home')
      })
    }
  });
}