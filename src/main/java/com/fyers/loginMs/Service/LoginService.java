package com.fyers.loginMs.Service;

import com.fyers.loginMs.dto.LoginRequestDto;
import com.fyers.loginMs.dto.RefreshTokenDto;

public interface LoginService {
    String getAccessToken(String clientId);
    String getNewAccessToken(LoginRequestDto loginRequestDto) throws InterruptedException;

    String generateAccessTokenUsingRefreshToken(RefreshTokenDto refreshTokenDto);
}
