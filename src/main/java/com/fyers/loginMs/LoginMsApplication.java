package com.fyers.loginMs;

import com.google.common.hash.Hashing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class LoginMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginMsApplication.class, args);
	}


	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(){
//		return (args)->{
//			String hash="17EY37RY6X-100:6327UUNK7I";
//			String output= Hashing.sha256().hashString(hash, StandardCharsets.UTF_8)
//					.toString();
//			System.out.println(output);
//		};
//	}
}
