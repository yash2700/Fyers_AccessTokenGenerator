package com.fyers.loginMs.controller;

import com.fyers.loginMs.dto.LoginRequestDto;
import com.fyers.loginMs.dto.RefreshTokenDto;
import com.fyers.loginMs.serviceImpl.LoginServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    private static final Logger logger= LoggerFactory.getLogger(LoginController.class);
    @Autowired
    LoginServiceImpl loginService;

//    @CircuitBreaker(name = "loginMS",fallbackMethod = "getAccessTokenFallback")
    @GetMapping("/getAccessToken")
    public ResponseEntity<String> getAccessToken(@RequestParam("appId") String appId){
        return new ResponseEntity<>(loginService.getAccessToken(appId), HttpStatus.OK);
    }

    @PostMapping("/newToken")
    public ResponseEntity<String> generateNewAccessToken(@RequestBody()LoginRequestDto loginRequestDto) throws InterruptedException {
        return new ResponseEntity<>(loginService.getNewAccessToken(loginRequestDto),HttpStatus.OK);
    }

    @PostMapping("/newTokenRefresh")
    public ResponseEntity<String> generateNewTokenUsingRefreshToken(@RequestBody() RefreshTokenDto refreshTokenDto){
        return new ResponseEntity<>(loginService.generateAccessTokenUsingRefreshToken(refreshTokenDto),HttpStatus.OK);
    }

//



}
