package com.fyers.loginMs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    private String clientId;
    private String appId;
    private String secretKey;
    private String redirectUrl;
    private String pin;

}
