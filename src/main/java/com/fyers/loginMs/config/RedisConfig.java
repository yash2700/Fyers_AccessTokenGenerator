package com.fyers.loginMs.config;

import com.fyers.loginMs.enums.ExceptionConstants;
import com.fyers.loginMs.exceptions.AccessTokenNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.swing.text.html.Option;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Configuration
public class RedisConfig {
    private final StringRedisTemplate redisTemplate;
    @Autowired
    public RedisConfig(StringRedisTemplate redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    public void createKeyValue(String key,String data){
        if(key.contains("accessToken")){
            LocalDateTime localDateTime=LocalDateTime.now();
            LocalDateTime localDateTimeMidnight=localDateTime.toLocalDate().atTime(LocalTime.MIDNIGHT);

            Duration duration=Duration.between(localDateTime,localDateTimeMidnight);
            long minutes=duration.toMinutes();
            if(minutes<0)
                    minutes+=1440;

            redisTemplate.opsForValue().set(key,data,Duration.ofMinutes(minutes));
        }
        else{
            redisTemplate.opsForValue().set(key,data,Duration.ofDays(14));
        }
    }

    public String getValueByKey(String key){
        String value= redisTemplate.opsForValue().get(key);
        return value;
    }

}
