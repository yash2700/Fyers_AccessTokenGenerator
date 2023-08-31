package com.fyers.loginMs.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class RestTemplateHelper {
    @Autowired
    RestTemplate restTemplate;
    @Value("${app_hash}")
    private String hash;
    @Value("${auth_url}")
    private String authUrl;

    public String getAccessToken(String authCode){
     AuthCodeRequest authCodeRequest= AuthCodeRequest.builder()
             .appIdHash(hash)
             .code(authCode)
             .grant_type("authorization_code")
             .build();
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AuthCodeRequest> authCodeRequestHttpEntity=new HttpEntity<>(authCodeRequest,httpHeaders);

        AuthCodeOutput response= restTemplate.postForObject(authUrl,authCodeRequestHttpEntity, AuthCodeOutput.class);
        System.out.println(response.toString());
        return response.getAccess_token()+" "+response.getRefresh_token();

    }
}
