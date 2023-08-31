package com.fyers.loginMs.config;

import com.fyers.loginMs.enums.ExceptionConstants;
import com.fyers.loginMs.exceptions.AccessTokenNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.swing.text.html.Option;
import java.time.Duration;
import java.util.Optional;

@Configuration
public class RedisConfig {
    private final StringRedisTemplate redisTemplate;
    @Autowired
    public RedisConfig(StringRedisTemplate redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    public void createKeyValue(String key,String data,int days){
        Duration duration=Duration.ofSeconds(days);
        redisTemplate.opsForValue().set(key,data,duration);
    }

    public String getValueByKey(String key){
        String value= redisTemplate.opsForValue().get(key);
        return value;
    }

}
