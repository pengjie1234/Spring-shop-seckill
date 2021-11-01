package com.duing.vo;

import lombok.Data;

@Data
public class GoodsVo {
    private String goodsId;
    private String goodsName;
    private String goodsType;
    private Double price;
    private String imgPath;
    private Double seckillPrice;
    private Integer stockNum;
}
