package com.fyers.loginMs.exceptions;

import com.fyers.loginMs.enums.ExceptionConstants;

public class AccessTokenNotFoundException extends RuntimeException{
    public AccessTokenNotFoundException(ExceptionConstants exceptionConstants){
        super(exceptionConstants.toString());
    }
}
