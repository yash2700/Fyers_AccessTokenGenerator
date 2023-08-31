package com.fyers.loginMs.serviceImpl;

import com.fyers.loginMs.Service.LoginService;
import com.fyers.loginMs.config.RedisConfig;
import com.fyers.loginMs.config.SeleniumConfig;
import com.fyers.loginMs.enums.ExceptionConstants;
import com.fyers.loginMs.exceptions.AccessTokenNotFoundException;
import com.fyers.loginMs.exceptions.OtpNotFoundException;
import com.fyers.loginMs.helper.RestTemplateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    RedisConfig redisConfig;

    @Autowired
    RestTemplateHelper restTemplateHelper;
    @Value("${app_id}")
    private  String app_id;
    @Value("${client_id}")
    private  String client_id;
    @Value("${secret_key}")
    private  String secret_key;
    @Value("${redirect_url}")
    private  String redirect_url;
    @Value("${client_pin}")
    private String pin;
    @Autowired
    SeleniumConfig seleniumConfig;

    @Override
    public String getAccessToken(String accessToken) {
        String value= redisConfig.getValueByKey(accessToken);
        if(value==null)
            throw new AccessTokenNotFoundException(ExceptionConstants.Access_Token_NotFound);
        return value;
    }

    @Override
    public String getNewAccessToken() throws InterruptedException {
        WebDriver webDriver=seleniumConfig.getWebDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://api-t1.fyers.in/api/v3/generate-authcode?client_id="+app_id+"&redirect_uri="+redirect_url+"&response_type=code&state=sample_state");
        webDriver.findElement(By.id("login_client_id")).click();
        webDriver.findElement(By.id("fy_client_id")).sendKeys(client_id);
        webDriver.findElement(By.id("clientIdSubmit")).click();

        Thread.sleep(30000);
        WebDriverWait wait=new WebDriverWait(webDriver,Duration.ofSeconds(30));
        String otpSixthDigit=webDriver.findElement(By.id("sixth")).getText();
        if(otpSixthDigit==null){
            throw new OtpNotFoundException(ExceptionConstants.Otp_Not_Found);
        }
        else{

            webDriver.findElement(By.id("confirmOtpSubmit")).click();
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("first")));
            Thread.sleep(2000);
            By firstXpath = By.xpath("//form[@id='verifyPinForm']/div[2]/input[1]");
            By secondXpath = By.xpath("//form[@id='verifyPinForm']/div[2]/input[2]");
            By thirdXpath = By.xpath("//form[@id='verifyPinForm']/div[2]/input[3]");
            By fourthXpath = By.xpath("//form[@id='verifyPinForm']/div[2]/input[4]");
            webDriver.findElement(firstXpath).sendKeys(Character.toString(pin.charAt(0)));
            webDriver.findElement(secondXpath).sendKeys(Character.toString(pin.charAt(1)));
            webDriver.findElement(thirdXpath).sendKeys(Character.toString(pin.charAt(2)));
            webDriver.findElement(fourthXpath).sendKeys(Character.toString(pin.charAt(3)));
            webDriver.findElement(By.id("verifyPinSubmit")).click();
            Thread.sleep(2000);
            webDriver.get("https://api-t1.fyers.in/api/v3/generate-authcode?client_id="+app_id+"&redirect_uri="+redirect_url+"&response_type=code&state=sample_state");
            String url=webDriver.getCurrentUrl();
            System.out.println(webDriver.getCurrentUrl());

            int lastIndex=url.lastIndexOf("&");
            int firstIndex=url.indexOf("auth_code");
            String auth_code=url.substring(firstIndex+10,lastIndex);

            System.out.println(auth_code);
            webDriver.quit();
            String accessToken= restTemplateHelper.getAccessToken(auth_code);
            redisConfig.createKeyValue("refresh",accessToken.split(" ")[1],14);
            return accessToken.split(" ")[0];

        }
    }


}
