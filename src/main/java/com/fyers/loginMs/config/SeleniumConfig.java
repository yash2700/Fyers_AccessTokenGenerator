package com.fyers.loginMs.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.time.Duration;

@Configuration
public class SeleniumConfig {
    @Value("${driver_path}")
    private String driver_path;

    public WebDriver getWebDriver() {
        System.setProperty("webdriver.chrome.driver",driver_path);
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver webDriver=new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return webDriver;

    }

}
