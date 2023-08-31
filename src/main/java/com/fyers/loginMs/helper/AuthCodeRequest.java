package com.fyers.loginMs.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthCodeRequest {

    private String grant_type;
    private String appIdHash;
    private String code;
}
