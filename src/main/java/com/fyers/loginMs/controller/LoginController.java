package com.fyers.loginMs.controller;

import com.fyers.loginMs.serviceImpl.LoginServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/login")
public class LoginController {
    private static final Logger logger= LoggerFactory.getLogger(LoginController.class);
    @Autowired
    LoginServiceImpl loginService;



    @CircuitBreaker(name = "loginMS",fallbackMethod = "getAccessTokenFallback")
    @GetMapping("/getAccessToken")
    public ResponseEntity<String> getAccessToken(@RequestParam("accessToken") String accessToken){
        return new ResponseEntity<>(loginService.getAccessToken(accessToken), HttpStatus.OK);
    }

    public ResponseEntity<String> getAccessTokenFallback(String accessToken, Throwable throwable) throws InterruptedException {
        logger.info("--------getting new access token!-----------");
        return new ResponseEntity<>(loginService.getNewAccessToken(),HttpStatus.OK);
    }
}
