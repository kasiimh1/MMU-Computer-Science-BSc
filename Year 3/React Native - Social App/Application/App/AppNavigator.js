//import required libraries 
import { createStackNavigator } from 'react-navigation-stack';
import { createAppContainer } from 'react-navigation';

//import screens 
import ComposeScreen from './Posting_Chits/Compose'
import LoginScreen from './User_Management/Login'
import SignupScreen from './User_Management/Signup'
import SettingScreen from './User_Management/Settings'
import SplashScreen from './SplashScreen'
import HomeScreen from './Posting_Chits/Home'
import AccountScreen from './User_Management/Account'
import SearchScreen from './Follower_Mangement/Search'
import MapScreen from './MapView'
import DraftScreen from './Posting_Chits/Draft'

//setup all the screens and their options (header for example)
const AppNavigator = createStackNavigator({
  Splash: { screen: SplashScreen, navigationOptions: { headerShown: false }, },
  Home: { screen: HomeScreen, navigationOptions: { headerShown: false }, },
  Account: { screen: AccountScreen, navigationOptions: { headerShown: false }, },
  Login: { screen: LoginScreen, navigationOptions: { headerShown: false }, },
  Signup: { screen: SignupScreen, navigationOptions: { headerShown: false }, },
  Compose: { screen: ComposeScreen, navigationOptions: { headerShown: false }, },
  Settings: { screen: SettingScreen, navigationOptions: { headerShown: false }, },
  Search: { screen: SearchScreen, navigationOptions: { headerShown: false }, },
  Map: { screen: MapScreen, navigationOptions: { headerShown: false }, },
  Draft: { screen: DraftScreen, navigationOptions: { headerShown: false }, },
});
export default createAppContainer(AppNavigator);
