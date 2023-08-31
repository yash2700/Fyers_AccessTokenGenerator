package com.fyers.loginMs.exceptions;

import com.fyers.loginMs.enums.ExceptionConstants;

public class OtpNotFoundException extends RuntimeException{
    public OtpNotFoundException(ExceptionConstants exceptionConstants){
        super(exceptionConstants.toString());
    }
}
