package com.duing.services;
import com.duing.vo.GoodsDateilVo;
import com.duing.vo.GoodsVo;

import java.util.List;

public interface GoodsService {
    List<GoodsVo> getGoods();
    GoodsDateilVo getGoodsDateil(String goodsId);
}
