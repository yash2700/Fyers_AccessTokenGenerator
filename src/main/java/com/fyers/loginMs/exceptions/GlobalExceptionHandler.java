package com.fyers.loginMs.exceptions;

import com.fyers.loginMs.error.ErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@PropertySource("classpath:messages.properties")
public class GlobalExceptionHandler {
    @Autowired
    Environment environment;
    private static final Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler({AccessTokenNotFoundException.class, OtpNotFoundException.class})
    public ResponseEntity<ErrorInfo> handleExceptions(Exception e){
        logger.error(environment.getProperty(e.getMessage()));
        return new ResponseEntity<>(
                ErrorInfo.builder()
                        .error(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                        .message(environment.getProperty(e.getMessage()))
                        .statusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
