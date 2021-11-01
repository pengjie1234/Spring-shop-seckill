package com.duing.services.impl;

import com.duing.mapper.GoodsMapper;
import com.duing.mapper.SeckillGoodsMapper;
import com.duing.model.Goods;
import com.duing.model.SeckillGoods;
import com.duing.services.GoodsService;
import com.duing.vo.GoodsDateilVo;
import com.duing.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;
    @Override
    public List<GoodsVo> getGoods() {
//        查询的商品表的数据
        List<Goods> goods = goodsMapper.getGoods();
        List<GoodsVo> goodsVoList = new ArrayList<>();
        for (Goods good : goods) {
//            根据商品id查询秒杀商品
            SeckillGoods sgm = seckillGoodsMapper.getSeckillGoodsById(good.getGoods_id());
//           good每行记录
            GoodsVo goodsVo = new GoodsVo();
            goodsVo.setGoodsId(sgm.getGoods_id());
            goodsVo.setGoodsName(good.getGoods_name());
            goodsVo.setGoodsType(good.getGoods_type());
            goodsVo.setPrice(good.getPrice());
            goodsVo.setImgPath(good.getImg_path());
            goodsVo.setSeckillPrice(sgm.getSeckill_price());
            goodsVo.setStockNum(sgm.getStock_num());
            goodsVoList.add(goodsVo);
        }
        return goodsVoList;
    }


    public GoodsDateilVo getGoodsDateil(String goodsId){
        Goods goods = goodsMapper.getGoodsById(goodsId);
        SeckillGoods seckillGoods = seckillGoodsMapper.getSeckillGoodsById(goodsId);
        GoodsDateilVo goodsDateilVo = new GoodsDateilVo();
//        商品信息
        goodsDateilVo.setName(goods.getGoods_name());
        goodsDateilVo.setGoodsId(goods.getGoods_id());
        goodsDateilVo.setImgPath(goods.getImg_path());
        goodsDateilVo.setPrice(goods.getPrice());

//        秒杀信息
        goodsDateilVo.setSeckillPrice(seckillGoods.getSeckill_price());
        goodsDateilVo.setStartTime(seckillGoods.getStart_time());
        goodsDateilVo.setEndTime(seckillGoods.getEnd_time());
        goodsDateilVo.setStockNum(seckillGoods.getStock_num());
        return goodsDateilVo;
    }
}
