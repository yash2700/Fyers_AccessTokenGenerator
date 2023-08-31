package com.fyers.loginMs.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {
    private String message;
    private String statusCode;
    private String error;
}
