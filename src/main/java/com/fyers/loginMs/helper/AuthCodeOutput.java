package com.fyers.loginMs.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthCodeOutput {
    private String s;
    private String code;
    private String message;
    private String access_token;

    private String refresh_token;
}
