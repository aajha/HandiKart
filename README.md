---
title: Cordova Guides
icon: cordova
docs: 
    buttonTitle: GUIDES
    primary: true
description: >
    The core service of the Mobile SDK is the Security Foundation (MASFoundation). The Security Foundation is responsible for authenticating your app to CA Mobile API Gateway.
toc: true
order: 1
breadcrumb:
    title: Guides
    type: option
titleBar:
    breadcrumb: true
button:
    title: < SEE ALL DOCS
    link: http://www.ca.com/us/developers/mas/docs.html
---
@[toc]


## MAS Foundation SDK Overview

The Mobile SDK provides simple and secure access to the services of the CA Mobile API Gateway. It consists of the following libraries:

::: Container width="350" align="center"
![React Native SDK](images/Mobile_SDK_Cordova.png)
:::

**MASFoundation**

The MASFoundation library:
- Authenticates users
- Handles user, app, and device registration, certificates request and stores them locally, keys, and token credentials for accessing the protected APIs
- ReactNative-MAS-Foundation Library

  ReactNative-MAS-Foundation is the core library for the Mobile SDK. 

  
  The ReactNative-MAS-Foundation library provides the following features:

  * User Authentication and Authorization
  * Customizable Login Dialogs
  * Enterprise Browser Support
  * Fingerprint Sessions Lock
  * One-Time Password (OTP)
  * Proximity Login (QR Code)
  * Single Sign-On
  * Social Login
  
  The GitHub location for the ReactNative-MAS-Foundation plugin is as follows:
  
  [ReactNative-MAS-Foundation Plugin](https://github.com/CAAPIM/ReactNative-MAS-Foundation/tree/master).
  

**MASUI**

The MASUI library provides resources to implement a user login dialog.

## Set Up Project and Start the SDK

To set up the project and start the Mobile SDK, follow these steps:

### Step 1: Review Prerequisites and Supported Versions

Before you use the Mobile SDK, the MAG Administrator must do the following:

* Install and configure CA Mobile API Gateway (MAG), CA API Management OAuth Toolkit, and (optional) backend services.
* Provide the client app file (msso_config.json).

#### Supported Versions

- **Platform**: React Native
- **React Native version**: 0.51.0


<a name="install-cordova"></a>
### Step 2: Install React Native

1. Download and install Node.js. Download the installer from the [NodeJS](https://nodejs.org/en/) website.

2. Install the React Native command-line utility. In the terminal, run the following command:
  
  ```
  npm install -g react-native-cli
  ```
3. (Optional) React Native recommends installing Watchman to ease the development procedure.
  
  ```
  brew install watchman
  ```


### Step 3: Set Up the Project

The following procedure describes how to set up the projects on iOS and Android platforms:

<a name="set-up-ios"></a>
#### Set Up the iOS Project
  
1. Run the following command in the terminal to create a React Native project:

  ```
  react-native init <PROJECT_NAME>
  ```

2. Change directory to <PROJECT_NAME>.

3. Run the following command in the terminal:
    
  ```
  npm install
  ```
       
4. Install React Native MAS Foundation Library 

  ```
  npm install react-native-mas-foundation --save
  ```
5. Link the `react-native-mas-foundation` native module < to waht>???
  
  ```
  react-native link react-native-mas-foundation
  ```

6. Add a valid msso_config.json to your Gateway assets folder of your project. For more information about `msso_config.json file`, see the [Add the msso_config.json File to Your Project](#add-msso-config-file) section. 

7. Download the MASFoundation frameworks from the [GitHub repository](https://github.com/CAAPIM/ReactNative-MAS-Foundation/tree/master). Drag and drop xcodeproj.

8. To use the UI screens for login, Enterprise browser, and One-Time Password, copy the OTP.js, ChannelSelctor.js, Login.js and EnterpriseBrowser.js from SDK's js/masui folder into the MASFoundationApp folder.

9. Run the application on a device, or simulator:

  ```
  react-native run-ios
  ```
::: alert info
**Note:** React Navigation - React Native does not have an inbuilt navigation feature. React Navigation is one of the most widely used navigation libraries. The ReactNative SDK for MAS depends on React Navigation library to be able to bring up the login, enterprise browser, and OTP screens.

React Navigation has a navigation manager named Stack Navigator. Stack Navigato is a method that takes a route configuration object as an input, and returns a React component.
:::

Sample code:

	```
	const App = StackNavigator({
	    Home: {
	        screen: HomeScreen,
	        navigationOptions: {
	            headerTitle: 'Home',
	        }
	    },
	    Login: {
	        screen: LoginScreen,
	        navigationOptions: {
	            headerTitle: 'Login',
	        }
	    }
	});
	```

To use the available screens (login, enterprise browser, and OTP) that are shipped with the React Native SDK, import them to the App.js.

```
import LoginScreen from './login.js';
import OTP from './OTP.js';
import ChannelSelector from './ChannelSelector.js';
import EnterpriseBrowserScreen from './EnterpriseBrowser.js';
```


In iOS, inaddition to the available screens, an enterprise browser storyboard is required to launch the Enterprise Browser. To use, drag enterprise browser storyboard from the ios folder to your project directory.



<a name="set-up-android"></a>
#### Set Up the Android Project


1. Run the following command in the terminal to create a React Native project:

  ```
  react-native init <PROJECT_NAME>
  ```

2. Change directory to <PROJECT_NAME>.

3. Run the following command in the terminal:
    
  ```
  npm install
  ```
       
4. Install React Native MAS Foundation Library 

  ```
  npm install react-native-mas-foundation --save

  ```

5. Link the `react-native-mas-foundation` native module < to waht>???
  
  ```
  react-native link react-native-mas-foundation
  ```

6. Add a valid msso_config.json to your Gateway assets folder of your project. For more information about `msso_config.json file`, see the [Add the msso_config.json File to Your Project](#add-msso-config-file) section.

7. Update the minSdkVersion in AndroidManifest.xml file to 19 or more if the Android Studio recommends to update based on the current version of the Android SDK.


8. Run the applicaiton on a device, or simulator:

  ```
  react-native run-android
  ```

9. React Native MAS Foundation Library uses the following native libraries from jcenter:

  * MasFundation
  * Masui

  To build your own .aar from latest code, see the [Build and Add Native SDKs](#abuild-and-add-native-sdks) section.


<a name="build-and-add-native-sdks"></a>
#### Build and Add Native SDKs

1. Download the [MASFoundation repository](https://github.com/CAAPIM/Android-MAS-SDK.git).

2. Build and generate .aar for mas-foundation and masui libraries.

3. Add the mas-foundation.aar and masui.aar in libs folder of react-native-mas-foundation, and sample app's libs folder.

4. Using Dependency Manager, update the build.gradle file in the react-native-mas-foundation library as follows:

  * Comment the following code in dependecies section:

    ```
    compile 'com.ca:mas:1.6.00'

    compile 'com.ca:masui:1.6.00'
    ```
    
  * Add the following dependency in the build.gradle file:
  
    ```
    compile(name: 'mas-foundation', ext: 'aar')

    compile(name: 'masui', ext: 'aar')
    ```
    
  * Add the following code in the repositories section

    ```
    flatDir {
            dirs 'libs'
        }
    ```

5. Similarly, add dependencies in the build.gradle file of your app as follows:
  
  ```compile(name: 'mas-foundation', ext: 'aar')

    compile(name: 'masui', ext: 'aar')
    
    
  * Add the following code in repositories section:

    ```
    flatDir {
      dirs 'libs'
    }
    ```

#### Add the msso_config.json File to Your Project 

Get the msso_config.json file from your MAG Administrator, and add it to your project. If you are using multiple MAGs, you may have more than one msso_config.json file. 

**Important!** The msso_config.json file contents must be in valid JSON format with the required data. If the file is not found, an error occurs and app will not start. Do not change any of the contents unless you know exactly what you are updating in the file. If you remove or alter required values, your application may not be able to connect or interact with the MAG server.

**iOS**
Place the msso_config.json file in the project directory.

**Android**
Place the msso_config.json file in the /assets directory.

**Sample msso_config.json file**
An example of a valid msso_config.json file is as follows:

  ```
  {  "server": {    "hostname": "gatewayhostname.example.com","pl"    "port": 8443,    "prefix": "",    "server_certs": [   ]  },  "mag": {    "system_endpoints": {      "device_register_endpoint_path": "/connect/device/register",      "device_remove_endpoint_path": "/connect/device/remove",      "client_credential_init_endpoint_path": "/connect/client/initialize"    },    "oauth_protected_endpoints": {      "enterprise_browser_endpoint_path": "/connect/enterprise/browser",      "device_list_endpoint_path": "/connect/device/list"    },    "mobile_sdk": {      "sso_enabled": true,      "location_enabled": true,      "location_provider": "network",      "msisdn_enabled": true,      "enable_public_key_pinning": false,      "trusted_public_pki": false,      "trusted_cert_pinned_public_key_hashes": [],      "client_cert_rsa_keybits": 1024    },    "ble": {      "msso_ble_service_uuid": "980c2f36-bde3-11e4-8dfc-aa07a5b089db",      "msso_ble_characteristic_uuid": "980c34cc-bde3-11r6-8dfc-aa07a5b093db",      "msso_ble_rssi": -35    }  },  "oauth": {    "client": {      "organization": "Example Organization Inc.",      "description": "Example App",      "client_name": "ExampleApp",      "client_type": "confidential",      "registered_by": "ExampleUser",      "client_ids": [        {          "client_id": "819455f6-9a7e-4ee0-b159-f85b25758112",          "client_secret": "",          "scope": "openid msso phone profile address email msso_client_register msso_register mas_messaging mas_storage mas_identity",          "redirect_uri": "https://ios.ssosdk.ca.com/ios",          "environment": "iOS",          "status": "ENABLED",          "registered_by": "ExampleUser"        }      ]    },    "system_endpoints": {      "authorization_endpoint_path": "/auth/oauth/v2/authorize",      "token_endpoint_path": "/auth/oauth/v2/token",      "token_revocation_endpoint_path": "/auth/oauth/v2/token/revoke",      "usersession_logout_endpoint_path": "/connect/session/logout",      "usersession_status_endpoint_path": "/connect/session/status"    },    "oauth_protected_endpoints": {      "userinfo_endpoint_path": "/openid/connect/v1/userinfo"    }  },  "custom": {    "oauth_demo_protected_api_endpoint_path": "/oauth/v2/protectedapi/foo",    "mag_demo_products_endpoint_path": "/protected/resource/products"  }}
```
  
### Step 4: Start the SDK

After you configure your project, get the SDK state, initialize and then start the SDK. This process includes loading the JSON configuration and initializing the SDK components.

To use the MAS Foundation APIs: 

  ```
  import {MASConstants, MAS, MASPluginUser, MASDevice, MASApplication } from 'react-native-mas-foundation';
  ```

### SDK Initialization States

Before initializing the SDK, it is useful to determine the current state of the SDK using the getMASSate method.

```MAS.getMASState(errorHandler, successHandler);
```
The following states can be returned during the lifecycle of the SDK:

* MASStateNotConfigured
  
  SDK is not initialized. No configuration file is available in the local file system based on the default configuration file name, or in the keychain storage.
  
* MASStateNotInitialized
  
  SDK is not initialized. Active configuration is available in the local file system, or keychain storage.
  
* MASStateDidLoad,
  
  SDK services are loaded. This state is invoked only one time for the app's lifecycle.

* MASStateWillStart
  
  SDK is preparing to start initializing all the elements required to operate.

* MASStateDidStart
  
  SDK is successfully initialized, and is functional.

* MASStateWillStop
  
  SDK is preparing to stop the lifecycle, and is shutting down all the elements and services.

* MASStateDidStop
  
  SDK is properly stopped. Restart the SDK. 

* MASStateIsBeingStopped
  
  SDK is forcibly stopped.
  
#### Initialization

The SDK needs to set login and OTP authentication callbacks before an API request. The callbacks are invoked if the requested API requires user login, or the API is OTP protected. The initialization does this job.

The initialization method is as follows:

  ```MAS.init(errorHandler, successHandler)
  ```

MASFoundation includes the following methods to start the SDK:

#### Start with Standard Method

  ```MAS.start(errorHandler, successHandler)
  ```

This MAS.start(errorHandler, successHandler) method starts the SDK with the currently-active configuration. A currently-active configuration can be the last successfully used configuration, the default JSON configuration file (for example, msso_config.json in the *assets* directory) or the custom JSON configuration file.

For the custom JSON configuration file, use the following method to specify a different file name to initialize the SDK:

  ```MAS.setConfigFileName('<The JSON Configuration File Name>', errorHandler, successHandler); 
  MAS.start(errorHandler, successHandler)
  ```

**Recommended for**: Most environments, including the production environments.

##### Start with Default Configuration

  ```MAS.startWithDefaultConfiguration(boolean, errorHandler, successHandler); 
  ```

This method starts the SDK with the currently-active configuration, or the default configuration (depending on the parameter). If you specify the **true** parameter, this overwrites the currently-active configuration with the default configuration (if two configurations are different.). If you pass the **false** parameter, the method's functionality is similar as **MAS.start();** . If the SDK is already initialized, this method stops the SDK, then restarts it with the custom JSON object.

**Recommended for**: Development environments where configurations change often.

##### Start with Custom JSON

  ```var jsonObjecxt = <A Json Object>
  MAS.startWithJSON(jsonObject, errorHandler, successHandler);
  ```
This MAS.startWithJSON(jsonObject, errorHandler, successHandler) method starts the SDK with the custom JSON object in, JSONObject. This method overwrites the currently-active configuration with the custom JSON object, and stores it as the active configuration. If the SDK is already initialized, this method: stops SDK, then it restarts it with the custom JSON object.

**Recommended for**: Multiple MAG servers, to dynamically change the configuration during runtime. The backend servers must have a version of the product that supports the dynamic configuration.

##### Start using file URL

  ```MAS.startWithURL(url, errorHandler, successHandler)
  ```
The MAS.startWithURL(url, errorHandler, successHandler); method starts the SDK with a msso_config.json configuration file. The msso_config.json configuration file is taken from the specified URL.


## Login: User Authentication and Authorization

**Library**: MAS, MASUI

**Description:** Authenticate and authorize users to log in to your app. This corresponds to the MAG backend service.

### Login and Password

React Native applications do not support the following features:

- BLE and NFC session sharing

These features are built in with the native [UI Templates](/docs/latest/reactnative/guides/#UI Templates) for authenticating the session. However, these features are not available in React Native SDK. A natively-built mobile app can still authorize sessions for a React Native app.

**Login**

To authenticate a user with username and password, use the following React Native API:

  ```MASPluginUser.loginWithUsernameAndPassword(username, password, errorHandler, successHandler); 
  ```

**Logout**

To log out a currently-authenticated user using an asynchronous request:

```user.logoutUser(errorHandler, successHandler);  
```

### Login and IdToken

**Login**

To authenticate a user with Idtoken, use the following React Native API:

```MASPluginUser. loginWithIdTokenAndTokenType(idToken, tokenType, errorHandler, successHandler); 
```

**Logout**

To log out a currently-authenticated user using an asynchronous request:

  ```user.logoutUser(errorHandler, successHandler);
  ```

### Login with MAS Auth Credentials

**Login with username password**

To authenticate a user with basic credentials.

  ```MASPluginUser. loginWithAuthCredentialsUsernamePassword(username, password, errorHandler, successHandler)
  ```

**Login with auth code**

To authenticate a user with auth code.

  ```MASPluginUser. loginWithAuthCredentialsAuthCode (authCode, errorHandler,  successHandler );  
  ```

**Login with JWT**

To authenticate a user with JSON Web Token (JWT). JWT is received after signing the claims.

  ```MASPluginUser. loginWithAuthCredentialsJWT(jwtToken, tokenType, errorHandler,  successHandler); 
  ```

### Login with auth code

```MASPluginUser.loginWithAuthCode (authCode, errorHandler, successHandler);  
```

### GetAuthCredentialsType

Returns the credential type.
```MASPluginUser.currentUser(function(error){
// No Authenticated user
// Handle error},
function(currentUser) {currentUser.lockSession(errorHandler, successHandler);})
```

### Fingerprint Sessions Lock

**Plugin:** ReactNative -MAS-Foundation

**Description**: An alternative to using passwords for login authentication, many phones support unlocking phones using fingerprint recognition. The Mobile SDK supports fingerprint authentication using local device authentication only. The user's fingerprint is compared against the image that is stored in the secure area on the chipset. If the unique characteristics of the fingerprints match, the user is authenticated, and the phone is unlocked. Local device authentication using fingerprints can store multiple fingerprints, including the owner and people who the owner trusts.  

::: alert danger 
**Important!** Currently, the Mobile SDK does not support fingerprint using multi-factor authentication, which is often mandated in government and enterprises (FIDO protocol). Specifically, the Mobile SDK does not match the device's fingerprint against an image that is stored securely on a server, and where the original fingerprint was scanned using a third-party fingerprint scanner. If you use the local device authentication using fingerprints, understand the inherent security limitations for this feature that are documented by your device vendor.
:::

**Support**: React Native Fingerprint functionality is supported for:
- iOS 9+ with Fingerprint and/or Passcode enabled device 
- Android 6.0+ with Fingerprint and/or Passcode enabled device

#### Device Lock/Unlock Session

The Mobile SDK supports session lock/unlock using device Fingerprint Session Lock, and device screen lock security (Passcode, Pattern, PIN, or Password). Because the app user can use one, both, or no locking method at all, you need to handle these scenarios. If the device is configured without any lock method, the Mobile SDK returns an error that device lock security is missing.

Let’s look at a user interaction with the Mobile SDK if you have configured both Fingerprint Session Lock and the screen lock method:
 
1. The device prompts user for fingerprint login.
2. If the user fails fingerprint authentication (three times), the device prompts for authentication using the device screen lock method.
3. If the user fails device screen lock authentication (five times) the device OS locks out the user for 30 seconds.

#### Fingerprint Implementation Highlights

- [user.lockSession] - Locks the user session.
- [user.isSessionLocked] - Verifies that the user session is locked.
- [user.unlockSession] - Unlocks the user session.
- [user.unlockSessionWithMessage] - Unlocks the user session with operation prompt message.
- [user.removeSessionLock] - Removes the user session lock.

::: alert info
**Note:** If you store multiple fingerprints on the device, all users can access the app and any API call. If you implement fingerprint with Single Sign-On enabled, all apps using Single Sign-On are blocked and require a fingerprint match to unlock.
:::

#### Lock User Session

```MASPluginUser.getCurrentUser(function(error) {
   // No Authenticated user 
   // Handle error}, function(currentUser) { 
     currentUser.lockSession(errorHandler, successHandler);}, 
```

#### Unlock User Session

```MASPluginUser.getCurrentUser(function(error) {
   // No Authenticated user   
   // Handle error}, function(currentUser) {      
   currentUser.unlockSession(errorHandler, successHandler);}
```

#### Unlock User Session with Operation Prompt Message

```MASPluginUser.getCurrentUser(function(error) {   
// No Authenticated user   
// Handle error}, function(currentUser) {      
currentUser.unlockSessionWithMessage(message, errorHandler, successHandler);}
```

#### Verify Locked User Session

```MASPluginUser.getCurrentUser(function(error) {   
// No Authenticated user   
// Handle error}, function(currentUser) {
currentUser.isSessionLocked(errorHandler, successHandler);}
```
#### Remove Locked User Session

```MASPluginUser.getCurrentUser(function(error) {   
// No Authenticated user   
// Handle error}, function(currentUser) {      
currentUser.removeSessionLock(errorHandler, successHandler);}
```

### Proximity Login

The Mobile SDK provides different ways to share an authenticated user with another device’s apps. It allows you to log into an enterprise app on one device, then use that device to login to the same or a different app on another device. For example, you provide user credentials to login to an app on a cell phone, then use the cell phone to login to a tablet. The devices can be running different operating systems.

**Limitation:** Currently, only the registered user on the device can use proximity login to authorize and provide access to another device.

#### Quick Response Code (QR Code)

**Library:** MASFoundation, MASUI

**Description:** Authenticate the user using a bar code.

MASFoundation provides a public interface to enable proximity login through the QR Code image. During registration or authentication, an app can display QR Code for the other registered and authenticated devices to scan, and the app proceeds with the code received from the server.

::: alert info
**Note:** Ensure to add the QR Code scanner package "react-native-qrcode-scanner" to your React Native application to scan the QR Code that the MAS generates.
:::

On scanning the QR Code generated on the MAS Login dialog, you get a string value similar to the following:

https://<i></i><hostname:port>/us/auth/device/authorize?sessionID=7b6b718a-b0a0-4c85-91ac-492ec9e0792a

#### Authenticate the device

Set the QRCode Authentication on MAG to display the QRCode on the login screen.

#### Scan the device

```
MAS.authorize(authURL, errorHandler, successHandler

::: alert info
**Note:** code is the authenticate URL outcome of QRCode scanning.
::
```

### Single Sign-On

To enable Single Sign-On, see the following Guides for either platform:
- [Android](http://mas.ca.com/docs/android/1.6.00/guides/)
- [iOS](http://mas.ca.com/docs/ios/1.6.00/guides/)

#### Web APPs Enterprise Browser

**Library:** MASFoundation

**Description:** Enable Single Sign-On to a trusted group of enterprise-approved apps on a device. Web apps are accessed through WebView and are not installed on the device. 

**To retrieve the Enterprise App List**

```
MASApplication.retrieveEnterpriseApps(errorHandler, successHandler)
```

Upon successful completion of the method, the successHandler receives an array of application objects. Else, the errorHandler receives an error object.


**To initialize the Enterprise Browser**

```
MASApplication.initEnterpriseBrowser(navigation, errorHandler, successHandler)
```
Upon successful completion, the SDK shows an Enterprise Browser pop-up. Else, the errorHandler receives an error object.

### Social Login

**Library**: MASFoundation, MASUI

**Description:** Access social login platforms. With Mobile SSO and social login enabled, users log in only once with their social account credentials, and can access multiple enterprise and third-party applications from a single mobile device. The Social Login authentication supports [PKCE](https://tools.ietf.org/html/rfc7636) extension to OAuth.

The MASUI library contains UI resource files for Social Login. You can customize the Activity view and login buttons by overwriting the social login provider images. The Mobile SDK provides the underlying logic to the backend services. The following social network site platforms are supported:

- Google
- Salesforce
- LinkedIn
- Facebook

::: Container width="300" align="center"
![Social Login](images/SocialLogin1.png)
:::

#### Create a Social Login Dialog (iOS)

The Mobile SDK uses the SFViewController interface to integrate with all provider social login SDKs. It handles the native social provider's authentication method whether tokens (id, access), or authorization code. This provides better security and a better user experience. 

Follow these steps to create a social login dialog:

::: alert info
**Note**: If you implemented social login using a previous version of the Mobile SDK, you must follow these steps to update your social login feature. The Mobile SDK no longer supports social login using the Mobile SDK native WebView.
:::

1. Register the URLs for all social login providers (URL scheme) in your app.
   The URLs must be a unique. For example:

   **Sample URL Scheme: /auth/oauth/v2/authorize**

```{
  "idp": "all",
  "providers": [
    {
      "provider": {
        "id": "facebook",
        "auth_url": "https://<unique_name>"
      }
    },
    {
      "provider": {
        "id": "google",
        "auth_url": "https://<unique_name>"
      }
    },
    {
      "provider": {
        "id": "salesforce",
        "auth_url": "https://<unique_name>"
      }
    },
    {
      "provider": {
        "id": "linkedin",
        "auth_url": "https://<unique_name>"
      }
    },
    {
      "provider": {
        "id": "enterprise",
        "auth_url": "https://<unique_name>"
      }
    },
    {
      "provider": {
        "id": "qrcode",
        "auth_url": "https://<unique_name>",
        "poll_url": "https://<unique_name>"
      }
    }
  ]
}
```

2. Ask your MAG administrator to register your URL scheme in OAuth Manager on the server side. 
   Admins add the URLs using the `redirect_uri` parameter in OAuth Manager.

3. Get the updated msso_json.config file with the URL Schemes.

::: alert warning 
**Important!** If the URL scheme is not registered properly on your application or in OAuth Manager, Social Login will not work.
:::
   
4. Add the `SFSafariViewController` view controller method to display the social login web page.  
   Load the third-party authentication provider's URL webpage in `SFSafariViewController` with the URL in `MASAuthenticationProvider`.

#### SFSafariViewController
 
```(void)presentFacebookLogin:(MASAuthenticationProvider *)provider {

  if ([provider.identifier isEqualToString:@"facebook"])
  {
    SFSafariViewController *viewController = [[SFSafariViewController alloc] initWithURL:provider.authenticationUrl];
    
    //
    // Set MASAuthorizationResponseDelegate
    //
    [[MASAuthorizationResponse sharedInstance] setDelegate:self];
    
    //
    // present the view controller
    //
    [self.navigationController presentViewController:viewController animated:YES completion:nil];
  }
}
```
5. Add the `MASAuthorizationResponse` method to handle app interactions.

   The `MASAuthorizationResponse` class handles incoming interaction from other social login apps (Safari), parses and conveys the authorization code, and handles errors that may occur when processing the designated class.

   In AppDelegate's `[UIApplicationDelegate application:openURL:options:]` method of your application, the `MASAuthorizationResponse` method must be invoked to properly handle incoming response from social login providers. 

#### MASAuthorizationResponse

```
- (BOOL)application:(UIApplication *)app openURL:(NSURL *)url options:(NSDictionary<UIApplicationOpenURLOptionsKey, id> *)options
{
    [[MASAuthorizationResponse sharedInstance] application:app openURL:url options:options];
    
    return YES;
}
```
6. Add a method to handle the authorization response by the MAG server.

   There are two methods to you can choose to handle the response from social login providers for authorization code (and any processing errors). You can either subscribe the `NSNotification`, or implement `MASAuthorizationResponseDelegate` protocols.

##### MASAuthorizationResponseDelegate

If you implement the delegation methods, you must set the delegation to the designated view controller or class. Then, implement the following methods to handle the authorization response.

```
/**
 *  Delegation method to notify with authorization code when the authentication process is done.
 *
 *  @param code NSString of authorization code
 */
- (void)didReceiveAuthorizationCode:(NSString *)code;

/**
 *  Delegation method to notify when an error is encountered during the authentication process.
 *
 *  @param error NSError object of the encountered error
 */
- (void)didReceiveError:(NSError *)error;
```

In you are using the custom login view controller or designated class, implement the following methods to handle login with authorization code in the response.

```(void)didReceiveAuthorizationCode:(NSString *)code {

  //
  // Use the authorization code to login the user
  //
  [MASUser loginWithAuthorizationCode:code completion:^(BOOL completed, NSError *error) {
    
    //
    // handle the result of login here
    //        
  }];
}

- (void)didReceiveError:(NSError *)error {
  
  //
  // Handle an error
  //
}
```

##### NSNotification to handle authorization response

If user is subscribing to `NSNotification` to handle the authorization response, subscribe: `MASAuthorizationResponseDidReceiveAuthorizationCodeNotification` and `MASAuthorizationResponseDidReceiveErrorNotification`.  Each notification delivers the respective contents to the objects listening to these notifications.

```

- (void)viewDidLoad {
  ...
  //
  // Subscribe the notification
  //
  [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(didReceiveAuthorizationCodeFromSocialLogin:)
                                                 name:MASAuthorizationResponseDidReceiveAuthorizationCodeNotification
                                               object:nil]; ...
}

- (void)didReceiveAuthorizationCodeFromSocialLogin:(NSNotification *)notification {

  NSString *authorizationCode = [notification.object objectForKey:@"code"];
      
  //
  // Use the authorization code to login the user
  //
  [MASUser loginWithAuthorizationCode:authorizationCode completion:^(BOOL completed, NSError *error) {
    
    //
    // handle the result of login here
    //        
  }];
}

```

#### Customize Social Login (Android)

::: alert info
**Note:** To support the social login authentication with the Google Chrome tabs, add the **compile 'net.openid:appauth:0.5.0'** dependency in the build.gradle file.
:::

##### Setup

1. If using AppAuth, you need to register the social login provider URLs in your application's `AndroidManifest.xml` for its class `RedirectUriReceiverActivity` and override the scheme with your chosen scheme name. Otherwise, the default scheme name in the MASUI manifest is `camsso`.

```xml
<activity
    android:name="net.openid.appauth.RedirectUriReceiverActivity"
    tools:node="replace">
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
       <data android:scheme="yourschemename" />
    </intent-filter>
</activity>
```

2. Ask your MAG administrator to register your URL scheme in OAuth Manager on the server side. Admins should add the URLs using the `redirect_uri` parameter in OAuth Manager.
3. Get the updated msso_json.config file with the URL schemes.

::: alert warning 
**Important!** If the URL scheme is not registered properly on your application or in OAuth Manager, Social Login will not work.
:::

### Login Screen Designer
 
**Description:** CA Mobile SDK now provides browser-based login page via Safari View Controller (iOS) or Chrome Custom Tabs (Android) giving the app developer flexibility to choose between native, or browser-based login page. If you have suite of mobile applications, browser-based login lets you dynamically change the login template of all the apps without modifying them individually. Using browser-based login flow the user is directed to customized login page for user authentication.
 

**Prerequisite:** Ensure to meet the following requirements for either platform:

* [iOS](http://mas.ca.com/docs/ios/1.6.00/guides/#login-screen-designer)

* [Android](http://mas.ca.com/docs/android/1.6.00/guides/#login-screen-designer)
  
  
#### Enable or Disable the Browser-Based Authentication to access the protected API
  
  Enable browser-based authentication in your app to load a templatized login dialog in a browser. The enableBrowserBasedAuthentication needs to be invoked before MAS start() API.
  ```
    MAS.enableBrowserBasedAuthentication(errorHandler, successHandler)
  ```
You can alternatively, launch a browser with a templatized URL.

#### Launch a browser to authenticate user

The API retrieves the templatized login page from MAG and display in a browser. This template has options to login and the API returns the login status. The URL is retrieved from the server endpoint that is mentioned in the configuration file.
  ``` 
   *  @param {function} successHandler user defined success callback which gets called after successful login
 *  @param {function} errorHandler user defined error callback 
 */
MASPluginUser.initializeBrowserBasedAuthentication(successHandler, errorHandler);
``` 

## UI Templates

The MASUI library contains graphics and resources files to speed up development time. It provides the following UI components:

- User Login Dialog
- React Native Login Dialog
- OTP Delivery Channel Selection Dialog
- React Native OTP Delivery Channel Selection Dialog
- OTP Authentication Dialog
- React Native OTP Authentication Dialog


**iOS**

The `MASFoundation.framework` and `MASUI.framework` are required by the ReactNative-MAS-Foundation plugin to implement the user login dialog.

**Android**

The ReactNative-MAS-Foundation plugin uses the MASUI library components by default. Ensure to add the MASUI library to your project to use the functionality.

If you do not want to use the native UI (MASUI), you can use the default React Native dialog.


### User Login Dialog

::: Container width="300" align="center"
![Social Login](images/User_Login.png)
:::

**Authenticate with user login dialog**

The MASFoundation library detects that the user credentials are required, and  uses the MASUI to display the user login dialog.

**Authenticate without user login dialog**

To authenticate directly without the user login dialog, use the following interface:

```;MASPluginUser.loginWithUsernameAndPassword( username, password, errorHandler, successHandler); 
```

### React Native Login Dialog


::: Container width="300" align="center"
![React Native Login](images/Login_Dialog.png)
:::

**Authenticate with React Native login dialog**

If Mas.init has been called previously, it will display the default React Native Login dialog instead of the MASUI native dialog.

### OTP Delivery Channel Selection Dialog

This default React Native Channel Selection is displayed if the MAS.init method is called.

::: Container width="300" align="center"
![React Native OTP Channel Selection](images/DeliveryChannel.png)
:::

### React Native OTP Delivery Channel Selection Dialog

This default React Native Channel Selection is displayed if the Mas.init method is called.

::: Container width="300" align="center"
![React Native Channel Selection](images/OTP_Channel_Selection.png)
:::

### OTP Authentication Dialog

This is the native MASUI page for entering the OTP and sending it to the MAG.

::: Container width="300" align="center"
![OTP Authentication](images/OTP2.png)
:::

### React Native OTP Authentication Dialog

This is the default HTML OTP Authentication page, which is displayed if Mas.init is called.

::: Container width="300" align="center"
![React Native OTP Authentication](images/OTP_Submission_Dialog.png)
:::

## MAS Lifecycle Methods

### MASFoundation APIs

This section provides the APIs for the MASFoundation.

::: alert info
**Note:** The successHandler and errorHandler parameters are common to all the MASFoundation APIs. The basic description of the parameters is as follows:

* successHandler: Specifies a user defined JavaScript callback function that is invoked by the plugin after the successful completion of the API call.

* errorHandler: Specifies a user defined JavaScript callback function that is invoked by the plugin when an error occurs.
:::

#### MASPlugin.MASObject

The MAS plugin object facilitates the start and end of the MASFoundation framework lifecycle. This class consists of several configuration settings for the React Native SDK.

**Lifecycle Methods**
  
*initialize*
  
Initializes the MAS plugin, and must be invoked when the React Native page loads.
  
```
Mas.initialize(errorHandler, successHandler);
```
    
*stop*
  
Stops the lifecycle of all the MAS processes.
    
```
MAS.stop(errorHandler, successHandler);      
    
```
    
**MAS Configuration Methods**
    
*useNativeMASUI*
    
Enables the native MASUI screens. Call this method when you do not use the out-of-the box and custom React Native UI components.
    
```
MAS.useNativeMASUI(errorHandler, successHandler);
    
```

**grantFlow**
    
Sets the device registration type as MASDeviceRegistrationType, and must be set before MAS start. 

```
MAS.grantFlow (MASGrantFlow, errorHandler, successHandler)
      
```
    
The MASGrantFlow can be any one of the following constant values:
* MASPlugin.MASGrantFlow.MASGrantFlowUnknown
* MASPlugin.MASGrantFlow.MASGrantFlowClientCredentials
* MASPlugin.MASGrantFlow.MASGrantFlowPassword
* MASPlugin.MASGrantFlow.MASGrantFlowCount

**configFileName**
  
Sets the name of the configuration file and gives the ability to set the file name to a custom value.
  
```
MAS.configFileName (fileName, errorHandler, successHandler)
    
```
    
::: alert info
**Note:** The filename is the location of the configuration file.
:::

**startWithDefaultConfiguration**
    
Starts the MASFoundation SDK using the msso-config.json file. This method overwrites the active configuration with the msso-config.json file, and stores it as the active configuration. If the SDK is already initialized, this method stops the MASFoundation SDK and restarts it with the custom JSON object.
    
```
MAS.startWithDefaultConfiguration (defaultConfiguration, errorHandler, successHandler)

```
  
::: alert info
**Note:** Set the defaultConfiguration value as true or false.
:::

**startWithJSON**
  
Starts the MASFoundation SDK with the JSON given in the jsonObject parameter. The method overwrites the active configuration with the JSON given in the jsonObject parameter, and stores it as the active configuration. If the SDK is already initialized, the method stops MASFoundation SDK and restarts it with the new JSON.
  
```
MAS.startWithJSON (jsonObject, errorHandler, successHandler) 
  
```
  
::: alert info
**Note:** The jsonObject must be a valid MAS JSON configuration string.
:::


**MAS Utility Method**

*gatewayIsReachable*

Verifies whether the MAG server is reachable.
  
```
MAS.gatewayIsReachable(errorHandler, successHandler)

```
  
#### MASPlugin.MASUser

**Utility Method**
 
*isAuthenticated*
  
Verifies that the user is authenticated.
 
```
  
MASPluginUser.getCurrentUser(function(error)  {

// No authenticated user present
// Handler error
,function(currentUser) {currentUser.isAuthenticated(errorHandler, successHandler);});
  
```
  
**FingerPrint Methods**
 
*isSessionLocked*

Verifies the session lock.

```
MASPluginUser. isSessionLocked(errorHandler, successHandler);MASPluginUser.getCurrentUser(function(error)  {

// No authenticated user present
// Handler error
,function(currentUser) {   currentUser.isSessionLocked(errorHandler, successHandler);});

```


*lockSession*
  
Locks the current session that can be unlocked using user's fingerprint.
  
```
  MASPluginUser.getCurrentUser(function(error {

// No authenticated user present
// Handler error
,function(currentUser) {   currentUser.lockSession (errorHandler, successHandler);});

```

*unlockSession*
  
Unlocks a locked session by displaying the lock screen.

```
MASPluginUser.getCurrentUser(function(error) {

// No authenticated user present
// Handler error
,function(currentUser) {   currentUser.unlockSession (errorHandler, successHandler);});

```
*unlockSessionWithMessage*
  
Unlocks a locked session by displaying the lock screen message.
  
```
MASPluginUser.getCurrentUser(function(error) {

// No authenticated user present
// Handler error
,function(currentUser) {   currentUser.unlockSessionWithMessage (errorHandler, successHandler);});

```
The message parameter specifies message that is displayed on the lock screen.


*removeSessionLock*
  
Removes the lock on a session.
  
```
MASPluginUser.getCurrentUser(function(error) {

// No authenticated user present
// Handler error
,function(currentUser) {   currentUser.removeSessionLock (errorHandler, successHandler);});

```
  
**User Lifecycle Methods:**
  
*loginWithImplicitFlow*
   
 Performs the implicit login by calling the endpoint that requires authentication by passing the given UserName and Password.

```
MASPluginUser.getCurrentUser(function(error) {

// No authenticated user present
// Handler error
,function(currentUser) {   currentUser.loginWithImplicitFlow (errorHandler, successHandler);});
  
```
*logoutUser*
  
logs off the logged in user.
  
```
MASPluginUser.currentUser(function(error) {

// No authenticated user present
// Handler error
,function(currentUser) {   currentUser.logout (errorHandler, successHandler);});


  ```
**User Utility methods**
  
*currentUser*
  
Gets the currently logged in user.

  ```
MASPluginUser.getCurrentUser(errorHandler, successHandler);

```
  
*getUserName*
  
Gets the username of the currently logged in user.

```
MASPluginUser.getCurrentUser(function(error) {

// No authenticated user present
// Handler error
,function(currentUser) {   return currentUser.userName;});

```
*isAuthenticated*

Verifies if the user is currently authenticated on the MAG server.
  
```
MASPluginUser.getCurrentUser(function(error) {

// No authenticated user present
// Handler error
,function(currentUser) {   return currentUser.isAuthenticated;});

```

*getAccessToken*

Gets the current user's access token.

```
MASPluginUser.getCurrentUser(function(error {
        
// No authenticated user present
// Handler error
,function(currentUser) {   return currentUser.getAccessToken;});

```
  
#### MASPlugin.MASDevice

**Instance Method**
    
*MASDevice*
      
Gets an instance of the MAS device.
    
**Device Lifecycle Methods**
    
*deregister*
   
Deregisters the device from the MAG server.
    
```
MASDevice.deregister(errorHandler, successHandler);

```
    
*resetLocally*
    
Clears all the tokens stored in the shared token store. 
    
:::alert warning

**Note:** Use this method with caution. This method deletes the client private key, effectively un-registers the device, and should only be used for testing or recovery purposes.
:::
    
```
MASDevice. resetLocally(errorHandler, successHandler);

```
**Device Utility Methods**
    
*getCurrentDevice*

Provides the registered device with the device identifier.
    
```
MASDevice. getCurrentDevice(errorHandler, successHandler);

```
*getDeviceIdentifier*
    
Provides the device identifier that confirms that the device is registered. 

```
MASDevice. getDeviceIdentifier(errorHandler, successHandler);

```
*isDeviceRegistered*
    
Verifies whether the MAS device is registered on the MAG server.

```
    
MASDevice. isDeviceRegistered(errorHandler, successHandler); 

```
    
#### MASPlugin.MASApplication

**Instance Method**
    
*MASApplication*
    
Gets an instance of the MAS application.

**Enterprise Browser Methods**

*initEnterpriseBrowser*
    
Opens the browsers with the list of enterprise applications.

```
MASApp.initEnterpriseBrowser(navigation, errorHandler, successHandler);
```

*retrieveEnterpriseApps*
  
Provides a list of enterprise applications.
  
```
MASApp.retrieveEnterpriseApps(errorHandler, successHandler);

```
      
*launchApp*
  
Launches an enterprise application.

```

MASApp.launchApp(appId, errorHandler, successHandler);

```
The appId parameter is Application ID of the enterprise application.

## Access APIs

### Send HTTP Requests to APIs

If you have custom endpoints installed on the MAG, you can make direct and secure HTTPS calls to the endpoints.

##### The endpoint

The endpoint passed to these methods should be a relative path. For example, if the complete URL is `https://somegatewayhost:port/some/endpoint`, you should only pass `/some/endpoint`.  The MASFoundation should already be setup for network connection to the base URL `https://somegatewayhost:port`.

###### The parameters

Parameters are encoded according to the various standards defined for the HTTP verb type.

###### The headers

Only adds the headers that are defined and required in your API. The Mobile SDK adds the necessary security credentials.

###### The request/response type

The following enumeration value is the request/response type.
Specifies the request and response types for all the HTTP requests.


```MASRequestResponseType:{
    /**
     * Standard JSON encoding.
     */
MASRequestResponseTypeJson:0,

    /**
     * SCIM-specific JSON variant encoding.
     */
MASRequestResponseTypeScimJson:1,

    /**
     * Plain Text.
     */
MASRequestResponseTypeTextPlain:2,

    /**
     * Standard WWW Form URL encoding.
     */
MASRequestResponseTypeWwwFormUrlEncoded:3,

    /**
     * Standard XML encoding.
     */
MASRequestResponseTypeXml:4
}
```

###### The response

```{  "MASResponseInfoBodyInfoKey": "<The response content>",
"MASResponseInfoHeaderInfoKey": { 
   "<header name>": "<Header value>" 
   }}
```

###### HTTP request parameters

Parameter             | Type                              | Example
--------              | ---                               | -----
requestPath           | String                            | /protected/resource/product
requestParam          | JSON                              | {"name":"value"}
requestHeader         | JSON                              | {"name":"value"}
requestType           | MASPlugin.MASRequestResponseType  | MASRequestResponseTypeJson
responseType          | MASPlugin.MASRequestResponseType  | MASRequestResponseTypeJson
isPublic              | BOOL                              | true


###### The request methods

HTTP DELETE calls to an endpoint:

```MAS.deleteFromPath(parametersArray, errorHandler, successHandler);
```

HTTP GET calls to an endpoint:

```MAS.getFromPath(parametersArray, errorHandler, successHandler);
```

HTTP POST calls to an endpoint:

```MAS.postToPath(parametersArray, errorHandler, successHandler);
```

HTTP PUT calls to an endpoint:

```MAS.putToPath(parametersArray, errorHandler, successHandler);
```
::: alert info

**Note:** Consider the following samples for the HTTP calls:

* The requestType and responseType parameters take only integer values. The requestType and responseType can have the 0, 1, 2, 3 or 4 values
  The value represents as follows:

  - 0 = JSON
  - 1 = SCIM_JSON
  - 2 = TEXT PLAIN
  - 3 = WWW FORM URL ENCODED
  - 4 = XML

* Sample requestParam is as follows:
  ``` 
  "{'paramKey1':'paramValue1','paramKey2':'paramValue2'}"
  ```
* Sample requestHeader is as follows:  
  ```
  "{'headerKey1':'headerValue1','headerKey2':'headerValue2'}"
  ```

* Sample requestPath is as follows:

  ```
   "/protectedApi"
   ```
 
* Sample requestType is as follows:
 
   ```
  <select name="requestType" >
          <option value="json">JSON</option>
          <option value="scimJSON">SCIM JSON</option>
          <option value="text">Plain Text</option>
          <option value="urlEncoded">URL Encoded</option>
          <option value="xml">XML</option>
          </select>
  ```             
* Sample responseType is as follows:

```             
<select name="responseType" >
        <option value="json">JSON</option>
        <option value="scimJSON">SCIM JSON</option>
        <option value="text">Plain Text</option>
        <option value="urlEncoded">URL Encoded</option>
        <option value="xml">XML</option>
                            </select>
```
:::

### Sign Requests Using JWT
**Description:** JWT is used for passing a signed message between the Mobile SDK and the MAG server. For Mobile Single Sign On, after a user logs in, the same token can be used to access other resources and services. JWT also provides message exchange and verification so you can verify that senders of information are, who they say they are. Implementing JWT ensures that hackers cannot tamper with data during message transmission, and cannot hack into one app, and then access all the user’s other apps. 
Sign claims using a default or a private key. The “claims” is a JSON object that has the following possible parameters:

Parameter        | Description
-------          |    ------
 iss             | Issuer     
 sub             | Subject of the JSON web token.
 aud             | Audience that the claim is intended for.
 exp             | Expiration time after which token becomes invalid.
 nbf             | Not before. The token is not accepted before this time.
 iat             | Issued at. The time at which the token was issued.
 jti             | JWT ID. Unique identifier.
 content         | Content of the JWT token.
 content-type    | Content type of the token.

```MAS.signWithClaims(claims, errorHandler, successHandler);
// Returns the a JWT token by signing the calims witht a default key.

MAS.signWithClaimsPrivateKey((claims, privateKey, errorHandler, successHandler);
// Returns a JWT by signing the claims with a specified private key.
```

### App Testing

The MAG server secures device registration and re-registration with this simple logic: only the previously-registered user or client can perform the re-registration. This logic (which resides in policy), is perfect for production environments. However, in Mobile SDK 1.5 and earlier, this caused "device already registered" errors during app testing with multiple users and uninstalling/reinstalling the app.

In this release, the Mobile SDK generates a new device identifier after uninstall/reinstall, which reduces the likelihood that you will get this error. 

But if you get this error, follow these steps to delete unwanted registered device entries in MAG Manager. If you do not have experience with MAG Manager, work with your Admin.
1. Log into the MAG Manager. For example: `https://your_hostname/instanceModifier/mag/manager`
2. Find your registered device.  
If you don’t know the device user, enter “*” in the “Lookup values for user”  field. 
3. Find your device identifier by calling this method in the Mobile SDK: [MASDevice currentDevice].identifier.
4. Map the device identifier to the OU attribute in MAG Manager (for example: OU=08f8ce12096fcf9d1a1779e4f9dc5fe15519fa2b4ace2af904cf954cc5f5c4e5), Registered Name (DN) column.
4. Click “Delete Device” to delete the device.

::: alert info
**Note**: It's not likely, but it's possible that the policy for device registration is incorrectly configured, so check with your Admin if you continue to get "device already registered" errors. See [Configure Device Registration](https://docops.ca.com/mag)
:::

:::alert info
**Note**: If you are using the default client credential registration, multiuser mode must be enabled on the MAG server. 
:::

## Debug the SDK

#### Error Response

Error response format is as follows:

```{
  "errorCode": 1234,
  "errorMessage": "<The error message>",
  "errorInfo" : "<The error info>"}
```

#### Rename the msso_config.json file

You can rename the msso_config.json configuration file, if you use the .json extension, and you change the name before you start the library processes.

```var configFileName = '<The configuration file name>'
mas.setConfigFileName(configFileName, errorHandler, successHandler);
```

#### Stop and Reset the device

To stop all processes in the library:

```mas.stopmas.stop(errorHandler, successHandler);
```

#### Reset all App, Device, and User Credentials

To reset all application, device, and user credentials in memory, or in the local and shared group keychains, use the following method: 

```MASDevice.resetLocally(errorHandler, successHandler);
```

::: alert info

**Note:** We recommend that you add a warning UI component or similar to indicate to the user exactly what they are doing, with a confirmation before proceeding with this action.
:::

::: alert info

**Note:** This only resets the credentials on the device. To reset and deregister the device record on the MAG, call MASDevice.getCurrentDevice().deregister() method.
:::

<a name="#troubleshoot-your-app"></a>
## Troubleshoot Your App

### Invalid Data Key

**Error:** Invalid data key
MAS Storage only supports datakeys that use upper and lowercase alphanumeric characters, plus these additional characters: - \ . __

### Reset the App

During app testing (or other administrative/devops use cases), you may need to reset the app and clean up the local cache on the device. Conditions that can lead to resetting the app include:
- You get a 'Device Unknown' error message.
- The device record has been removed on the MAG.
- You get an error message that the device is already registered.

Use the following method to deregister the device and remove the record on MAG. Note that all apps associated with the device are deregistered. 

#### Disable PKCE

Proof Key for Code Exchange (PKCE) provides an extra layer of security for your app. You can enable, disable, or check its state. It is enabled by default and works with proximity login. Your Administrator does not need to enable the feature on the MAG server. In the enabled state, the Mobile SDK responds to authentication requests or not, based on the policy that is configured by your Admin using OAuth Toolkit. We recommend leaving this feature enabled.

```
MAS.enablePKCE(booleanValue, errorHandler, successHandler);
```
It takes true or false parameter value. If you have a specific use case to disable it, go to Reference documentation and change the state.

#### Deregister a device

```MASDevice.getCurrentDevice().deregister(new MASCallback<Void>() {
    @Override
    public void onSuccess(Void object) {
        //The device is successfully de-registered.
    }

    @Override
    public void onError(Throwable e) {
        //Handle the error
    }
 });
```

## ADVANCED USE CASES

This section provides solutions that solve specific and immediate customer requests. They may not have the tight coupling between backend and the SDK that we normally provide, but they work. They just require more collaboration between Admins and developers to implement. Hope you find them useful!

### Send HTTP Requests to External APIs

You can send HTTP requests to APIs that are hosted on others servers (another MAG, or other public server).
```MAS.getFromPath(parameterArray, errorHandler, successHandler); 
```

#### Create the MASSecurityConfiguration object

To send HTTP requests to another MAG or public server, configure the MASSecurityConfiguration object. The following security settings are per hostname and port number:

Attribute             | Description                              
--------              | ---                               
Host                  | Host name of the server.          
isPublic              | Boolean value that specifies whether the server is public or not.                             
trustPublicPKI        | Boolean value to trust, or not to trust the public PKI. If false, provide either the certificate, or the publicKeyHash.                              
certificate           | The array of string that contains the certificate chain. (like in the msso_config.json file)  
publicKeyHashe        | String value of the public key hash.                            


#### Get Public Key Hash

Follow the steps to extract the public key hash of a server.

1. Open a terminal. Enter the following OpenSSL command to extract the certificates from the host, and store them in a .pem file.
  ```
  $ sudo echo -n | openssl s_client -connect HOSTNAME.com:443 | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > ./HOSTNAME.pem
  ```
  List the current folder to view the HOSTNAME.pem file.

2.  Extract the public key hash from the HOSTNAME.pem file using the following OpenSSL command:
  ```
  $ openssl x509 -in HOSTNAME.pem -pubkey -noout | openssl rsa -pubin -outform der | openssl dgst -sha256 -binary | base64
  ```

3.  Copy the public key hash from the command:
  ```
  Usage: MAS.setSecurityConfiguration(securityConfig, errorHandler, successHandler);
  ```
  Where securityConfig looks as follows:
  ```
  SecurityConfig: {
  host: '',
  isPublic: false,
  trustPublicPKI: false,
  certificates: [],
  publicKeyHashes: []
  }
  ```
  While making the API calls to the servers, set the isPublic parameter of the REST call to ‘true’.

### Validate Data Recipients Using JWT

This solution describes how to use JSON Web Token (JWT) to validate recipients using the Mobile SDK and MAG.

#### Why JWT?

JWT is an open standard (RFC 7519) that is used to pass information between parties for web apps. Here is a quick summary of why JWT is used for mobile apps.

| Benefit                                 | Description                                                                                                                                                                                                                                                                                                   |
|-----------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Self contained                          | The tokens are designed to be compact, self-contained, and URL-safe. A JWT consists of a header, payload (usually user information), and a signature. <br>![JWT](images/jwt.png)</br> <br>JWTs can be signed using a secret using an HMAC algorithm or a public/private key pair using algorithms such as RSA. For HMAC, the secret is known on the client and the server. For RSA, the signing party owns the private key, and the validating party knows the public key. </br> |
| Easily passed                           | JWTs can be passed as a header or parameter for authenticating users and securing data.                                                                                                                                                                                           |
|                                         |                                                                                                                                                                                                                                                                                                               |
| Multiple programming language support | JWT supports many programming languages including .NET, Python, Node.js, PHP, Java, Ruby, Go, Haskell, and JavaScript.                                                                                                                                                                                      |                                                                                                                                                 |
#### More JWT Resources

- [Introduction to JWT](https://jwt.io/introduction/)
- [Digitally Signing Your JSON Documents](https://jwt.io/introduction/)
- [Nimbus JOSE + JWT](https://connect2id.com/products/nimbus-jose-jwt)

#### Business Value
Modern apps must be able to access and co-mingle with other third-party app data. JWT can validate that "someone is who they say they are,"  which adds  another layer of security beyond mutual SSL and OAuth. Key capabilities include:

- **Authentication for MSSO**  
User authentication is the most common use case for using JWT. JWT is widely used by Mobile Single Sign On (MSSO) because of its small overhead and ease-of-use across different domains. For example, after a user logs in, the token that is used for login can permit the user to access other resources, routes, and services.

- **Message exchange and verification**  
JWT allows secure data exchange between parties. Implementing JWT can stop MSSO-related, "man in the middle" hacking scenarios.  For example, a user logs in to app A, a hacker accesses app A, and then gets access to all other apps (B, C, and so on). Signed JTW ensures that the content has not been tampered with. And if you are implementing digital signatures, JWT is used to pass the signed message between the Mobile SDK and the MAG server.

#### Use Cases

Let's say a bank offers a mobile banking app. And a user wants to transfer money from their account to a brother's account. Or the user wants to withdraw a huge amount of money to buy a house. The mobile banking app needs a "funds transfer" API. From a bank perspective, mutual SSL and OAuth may not be enough. Banks may want to validate that the transfer is being made from the correct sender (and not someone impersonating the sender). In this example, using JWT for digital signatures can provide this extra layer of security. Just like anyone can read a contract to buy a house ,but the user signature on the contract is the validation of trust for the final transaction.

#### Support and Limitations

The Mobile SDK includes:
- Methods for developers to create signed JWT   
  - MAS.signWithClaims(claims, errorHandler, successHandler);
  - MAS.signWithClaimsPrivateKey(claims, privateKey, errorHandler, successHandler);


For help with server-side JSON Web Token assertions, see the [CA API Gateway documentation](https://docops.ca.com/GATEWAY).

**Limitations**

The Mobile SDK generates a key pair, and verifies the JWT. To fully implement JWT, Admins must create their own policy and solution. Specifically, this version does not support these client and server features:
- Methods to generate your own key pair (private/public)
- Methods to verify a signed JWT 
- Methods to encrypt the signed JWT
- Signing a payload to more than one MAG server
- Other hashing algorithms for signing payloads other than RS256 
- Signing query parameters
- Signing using fingerprint private key

#### Implementation

All workflows for Cordova validate that:

- Messages are created by an authenticated sender; the sender cannot deny having sent the message
- Message content is not altered during transit after receiving a JWT

##### Sign doc: with default private key

The following diagram shows a workflow for signing a document using the default private key. 

::: Container width="600" align="left"
![Custom Private Key](images/SigningwithDeviceRegistration.png)
:::

In this workflow:

1. The client generates the private/public key pairs
2. The client sends a CSR and receives the signed public key from the MAG server (at the end of the registration flow)
3. The client uses the payload to create a signed JWT using its private key
4. The client sends the JWT to the MAG server
5. The MAG server validates the JWT with the registered public key

##### Sign doc: with custom private key

The following diagram shows the workflow for signing a document using device registration using a custom private key. 

::: Container width="500" align="left"
![Default Private Key](images/GenericDocSigningFlow.png)
:::

#### JWT Claims

The Mobile SDK injects the following default claims and values into the JWT; they represent a minimum set to validate the JWT. All claims are optional and customizable; the developer can override all defaults.

| Claim          | Description                                                  | MAG Example                                                                |
|----------------|--------------------------------------------------------------|----------------------------------------------------------------------------|
| iss         | Issuer of the JWT.                                           | The MAG identifier and the client id. <br>device://{mag-identifier}/{client_id}</br> |
| aud       | Audience or recipient(s) of the JWT.                                     | The MAG server. <br><https://mag2.ca.com></br>                                     |
| sub        | Subject of the JWT (user who is associated with the action). | 'username' or 'client_id' based on the currently-authenticated session.    |
| exp | Expiration time. Do not process JWT claim on/after this time.                 | 148761933. <br>Default = 5 minutes from when the request was issued.</br>         |
| jti         | A unique identifier for the JWT.                             | A randomly -generated UUID. <br> 4c3c2eca-f7a3-11e6-bc64-92361f002672 </br>          |
| iat     | Issued at time. Time when the JWT was created.                               | 1487619335                                                                 |
| nbf  | Not before time. Do not process JWT claim before this time.                   | 1487619335                                                                 |
| content        | Payload data that you want to sign using Put, Post, Delete.  | {"test":"value"}                                                           |
| contentType    | Data content type.                                           | application/json                                                           |

#### Sample Payloads

| Data Format                                | Content Type                       |
|--------------------------------------------|------------------------------------|
| {        "test": "value" }                 | application/json                   |

**Server Received JWT**

**Header**  
{"alg":"RS256"}

**Payload**   
```
{
  "iss": "device://${mag-identifier}/${client_id}",
  "aud": "https://mag2.ca.com",
  "sub": "karek",
  "exp": 148761933,
  "jti": "4c3c2eca-f7a3-11e6-bc64-92361f002672",
  "iat": 1487619335,
  "content": {
    "test": "value"
  },
  "content-type" : "application/json"
}
```

| Data Format | Content Type |
|-------------|--------------|
| "test"      | text/plain   |

**Header**  
{"alg":"RS256"}

**Payload**  
```
{
  "iss": "device://${mag-identifier}/${client_id}",
  "aud": "https://mag2.ca.com",
  "sub": "karek",
  "exp": 148761933,
  "jti": "4c3c2eca-f7a3-11e6-bc64-92361f002672",
  "iat": 1487619335,
  "content": "test",
  "content-type" : "text/plain"
}
```

| Data Format | Content Type                     |
|-------------|----------------------------------|
| Bytes       | application/octet-stream or none |

**Header**  
{"alg":"RS256"}

**Payload**   
```
{
  "iss": "device://${mag-identifier}/${client_id}",
  "aud": "https://mag2.ca.com",
  "sub": "karek",
  "exp": 148761933,
  "jti": "4c3c2eca-f7a3-11e6-bc64-92361f002672",
  "iat": 1487619335,
  "content": "<base 64 encoded>",
  "content-type" : "image/png"
}
```

| Data Format                        | Content Type                      |
|------------------------------------|-----------------------------------|
| fromAcc=1234&toAcc=2345&amount=100 | application/x-www-form-urlencoded |

**Header**  
{"alg":"RS256"}


**Payload**   
```
{
  "iss": "device://${mag-identifier}/${client_id}",
  "aud": "https://mag2.ca.com",
  "sub": "karek",
  "exp": 148761933,
  "jti": "4c3c2eca-f7a3-11e6-bc64-92361f002672",
  "iat": 1487619335,
  "content": {
    "fromAcc": [
      "1234"
    ],
    "toAcc": [
      "2345"
    ],
    "amount": [
      "100"
    ]
  },
  "content-type": "application/x-www-form-urlencoded"
}
```
#### Frequently Asked Questions

**Can I combine the workflows (default private key and custom private key)?**  
Yes. Both these methods can be combined. Claims are overridden in the order they are called.

**Do I need to implement any new assertions to consume the JWT that the Mobile SDK generates?**  
No. You can use the existing CA API Gateway assertions, Encode/Decode Json Web Token.

## collapsible markdown?
<details><summary>CLICK ME</summary>
<p>
#### yes, even hidden code blocks!
print("hello world!")
</p>
</details>
