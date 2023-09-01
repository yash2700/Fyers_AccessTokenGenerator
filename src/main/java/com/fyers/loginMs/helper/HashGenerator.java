package com.fyers.loginMs.helper;

import com.google.common.hash.Hashing;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;

@Data
@NoArgsConstructor
@Builder
public class HashGenerator {
    public static String generateSha256Hash(String token){
        return Hashing.sha256().hashString(token, StandardCharsets.UTF_8).toString();
    }
}
