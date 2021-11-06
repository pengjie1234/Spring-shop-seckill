package com.duing.services;

public interface RedisService {
     void initData(String goodsId,int stockNum);
     void initData();
     String decr(String userId,String goodsId);
     String handle(String userId,String goodsId);
     String handleTransaction(String userId, String goodsId);
}
