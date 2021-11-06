package com.duing.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
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
//    获取redis的value
    public Object get(String key){
        if (!redisTemplate.hasKey(key)){
            return null;
        }
      return redisTemplate.opsForValue().get(key);
    }
//    减少库存
    public void decr(String key){
        redisTemplate.opsForValue().decrement(key);
    }
//    redis事务
    public Object execute(SessionCallback sessionCallback){
        return redisTemplate.execute(sessionCallback);
    }

//    判断是否有key值
    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }


}
