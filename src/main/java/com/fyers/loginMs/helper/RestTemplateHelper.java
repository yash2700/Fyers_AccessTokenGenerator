package com.fyers.loginMs.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class RestTemplateHelper {
    @Autowired
    RestTemplate restTemplate;
    @Value("${auth_url}")
    private String authUrl;
    @Value("${refresh_url}")
    private String refresh_url;


    public String getAccessToken(String authCode,String hash){
     AuthCodeRequest authCodeRequest= AuthCodeRequest.builder()
             .appIdHash(hash)
             .code(authCode)
             .grant_type("authorization_code")
             .build();
        System.out.println(authCode);
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        System.out.println(hash);
        HttpEntity<AuthCodeRequest> authCodeRequestHttpEntity=new HttpEntity<>(authCodeRequest,httpHeaders);

        AuthCodeOutput response= restTemplate.postForObject(authUrl,authCodeRequestHttpEntity, AuthCodeOutput.class);
        System.out.println(response.toString());
        return response.getAccess_token()+" "+response.getRefresh_token();
    }

    public String getAccessTokenByRefreshToken(String refresh_token,String hash,String pin){
        RefreshTokenRequest refreshToken= RefreshTokenRequest.builder()
                .appIdHash(hash)
                .refresh_token(refresh_token)
                .grant_type("refresh_token")
                .pin(pin)
                .build();

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RefreshTokenRequest> refreshTokenHttpEntity=new HttpEntity<>(refreshToken,httpHeaders);

        RefreshTokenOutput refreshTokenOutput=restTemplate.postForObject(refresh_url,refreshTokenHttpEntity, RefreshTokenOutput.class);

        return refreshTokenOutput.getAccess_token();
    }
}
