package com.fyers.loginMs.Service;

public interface LoginService {
    String getAccessToken(String accessToken);
    String getNewAccessToken() throws InterruptedException;
}
