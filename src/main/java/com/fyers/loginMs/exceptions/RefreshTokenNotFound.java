package com.fyers.loginMs.exceptions;

import com.fyers.loginMs.enums.ExceptionConstants;

public class RefreshTokenNotFound extends RuntimeException{
    public RefreshTokenNotFound(ExceptionConstants exceptionConstants){
        super(exceptionConstants.toString());
    }
}
