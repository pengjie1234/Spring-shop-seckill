package com.duing.mapper;

import com.duing.model.Goods;
import com.duing.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {
    List<Goods> getGoods();
    List<Goods> selectGoods();
    Goods getGoodsById(String goodsId);
}
