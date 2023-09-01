
# Fyers_AccessTokenGenerator

Fyers_AccessTokenGenerator is an open source Java library that can generate Access Tokens for consuming Fyers Api. It uses Selenium to do the Browser login Automation. Also it can generate Access Token using Refresh Token. Redis cache is a must.



# How to use it



## Download and build the code

```bash
  git clone https://github.com/yash2700/Fyers_AccessTokenGenerator
  
  mvn clean install
```
    
## Login -- Step1

To create Access Token you need to hit the Rest Api endpoint 
```bash
  http://localhost:8000/login/newToken
```
with Request Body as follows

```bash
    String clientId;
    String appId;
    String secretKey;
    String redirectUrl;
    String pin;
```

you also need to enter the otp received on your mobile number.

By default when you do this a Refresh Token is generated and store in cache with a validity of 14 days. so that we can simplify the Access token Generation process.

## If Access Token is present in cache

 The Generated Access Token is store in cache with a validity upto midnight. So that you need not to concern about creating a new Access Token and go through the process of step 1. To access this feature you can use the Rest Api endpoint. Generally Access Token has a validity until midnight of that particular day.

```bash
    http://localhost:8000/login/getAccessToken?appId="your-App-Id"
```
In the above request you need to send the app Id to get the respective Access Token.
## If Access token is not present -- Using Refresh Token

If Access token is not present we have to use the Refresh Token. So that we can skip the browser login process.

The respective Rest Api End point for this operation is:

```bash
    http://localhost:8000/login/newTokenRefresh
```

you have to provide some details as Request Body to proceed---

```bash
     String appId;
     String secretKey;
     String pin;
```

If there's no Refresh Token present in the cache you have to follow the Step 1.
## Please Note**

** 1. Please read the code or test case first to know the pre-requisite before using any command **

** 2. The code is written for a specific purpose and can further be generalized. Especially Login flow needs to be followed as per current implementation. Login flow is quite similar to how is traversed through web portal (i.e. login via user and password and provide 4 digit pre-defined pin) **

** 3. Logout flow still requires some more exploration **


** 5. Read the official docs very well! Sometimes you might see a failure in API response but it MIGHT have actually got executed successfully. **

**

For any queries, please write me at yaswnthreddyboggala@gmail.com.
