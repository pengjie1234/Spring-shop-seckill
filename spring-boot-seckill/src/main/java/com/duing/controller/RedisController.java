package com.duing.controller;

import com.duing.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;
//    @GetMapping("/initData")
//    public String initData(String goodsId,int stockNum){
//        System.out.println(redisService);
//        redisService.initData(goodsId,stockNum);
//        return "success";
//    }


        @GetMapping("/initData")
        public String initData(){
            redisService.initData();
            return "success";
        }
    @GetMapping("/seckillAPI")
    public String decr(String userId,String goodsId){
        String result = redisService.decr(userId,goodsId);
        System.out.println(result);
        return result;
    }
}
