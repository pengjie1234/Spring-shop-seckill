package com.duing.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
//设置缓存
    public void set(String key,Object value){
        redisTemplate.opsForValue().set(key,value);
    }
//    设置缓存并设置有效时间
    public void set(String key,Object value,long timeOut){
        redisTemplate.opsForValue().set(key,value,timeOut, TimeUnit.SECONDS);
    }
    public Object get(String key){
        if (!redisTemplate.hasKey(key)){
            return null;
        }
      return redisTemplate.opsForValue().get(key);
    }
}
