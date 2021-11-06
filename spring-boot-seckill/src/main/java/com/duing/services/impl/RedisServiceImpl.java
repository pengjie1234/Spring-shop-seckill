package com.duing.services.impl;

import com.duing.mapper.SeckillGoodsMapper;
import com.duing.model.SeckillGoods;
import com.duing.services.RedisService;
import com.duing.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
     private RedisUtil redisUtil;
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    @Override
    public void initData(String goodsId,int stockNum) {
        System.out.println(goodsId);
       redisUtil.set(goodsId + "_count",stockNum);
    }

    @Override
    public void initData() {
//        将mysql数据库中的数据同步到redis数据库中，并起一个名字
        List<SeckillGoods> goods = seckillGoodsMapper.findSeckillGoods();
        for (SeckillGoods good : goods) {
            String goodsId = good.getGoods_id();
            redisUtil.set(goodsId + "_count",good.getStock_num());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start_time = good.getStart_time();
            String startTime = simpleDateFormat.format(start_time);
            redisUtil.set(goodsId+ "_startTime",startTime);
            Date end_time = good.getEnd_time();
            String endTime = simpleDateFormat.format(end_time);
            redisUtil.set(goodsId + "_endTime",endTime);


        }
    }

    @Override
    public String decr(String userId, String goodsId) {
          return handle(userId,goodsId);
//        return handleTransaction(userId,goodsId);
    }
//    普通秒杀库存方法
    @Override
    public String handle(String userId,String goodsId){
        if (userId == null || goodsId == null){
            return "参数异常";
        }
        System.out.println(userId);
        System.out.println(goodsId);
        String startTime = (String) redisUtil.get(goodsId+ "_startTime");
        String endTime = (String) redisUtil.get(goodsId + "_endTime");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = null;
        Date end = null;
        try {
            start = format.parse(startTime);
            end = format.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        输出秒杀开始时间
        System.out.println(start);
        System.out.println(end);
        if (start == null || new Date().before(start)){
            return "秒杀未开始";
        };
        if (end == null || new Date().after(end)){
            return "秒杀已结束";
        };
        int stockNum = (int) redisUtil.get(goodsId + "_count");
        if (stockNum <= 0){
            return "库存没有了";
        };
        if (redisUtil.get(userId + "_" + goodsId) != null){
            return userId +"已秒杀过了";
        };

//        减库存
        redisUtil.decr(goodsId + "_count");
//        生成订单
        redisUtil.set(userId + "_" + goodsId,1);
        return userId + "秒杀成功";
    }
//redis事务开启
    @Override
    public String handleTransaction(String userId, String goodsId) {
        String startTime = (String) redisUtil.get(goodsId+ "_startTime");
        String endTime = (String) redisUtil.get(goodsId + "_endTime");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = null;
        Date end = null;
        try {
            start = format.parse(startTime);
            end = format.parse(endTime);
        } catch (Exception e) {

        }
        System.out.println(start);
        System.out.println(end);
//        输出秒杀开始时间
        if (start == null || new Date().before(start)){
            return "秒杀未开始";
        };
        if (end == null || new Date().after(end)){
            return "秒杀已结束";
        };
        if (redisUtil.get(goodsId + "_" + userId) != null){
            return userId +"已秒杀过了";
        };
        System.out.println("1111111");
        SessionCallback sessionCallback = new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.watch(goodsId + "_count");
                int stockNum = (int) redisUtil.get(goodsId + "_count");
                if (stockNum <= 0){
                    return "库存没有了";
                };
                operations.multi();
                redisUtil.decr(goodsId + "_count");
                redisUtil.set(goodsId + "_" + userId,1);
                return operations.exec();
            }
        };
        System.out.println("5555555555");
        redisUtil.execute(sessionCallback);
        if (redisUtil.hasKey(goodsId + "_" + userId)){
            return userId + "秒杀成功了";
        }else {
            return userId + "秒杀失败";
        }

    }


}
