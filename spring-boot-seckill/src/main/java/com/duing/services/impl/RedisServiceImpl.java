package com.duing.services.impl;

import com.duing.mapper.SeckillGoodsMapper;
import com.duing.model.SeckillGoods;
import com.duing.services.RedisService;
import com.duing.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<SeckillGoods> goods = seckillGoodsMapper.findSeckillGoods();
        for (SeckillGoods good : goods) {
            String goodsId = good.getGoods_id();
            redisUtil.set(goodsId + "_count",good.getStock_num());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start_time = good.getStart_time();
            String startTime = simpleDateFormat.format(start_time);
            redisUtil.set(goodsId+ "_startTime",startTime);
        }
    }
}
