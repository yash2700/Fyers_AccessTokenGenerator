package com.fyers.loginMs.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenOutput {
    private String s;
    private String code;
    private String message;
    private String access_token;
}
