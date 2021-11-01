package com.duing.vo;

import lombok.Data;

import java.util.Date;

@Data
public class GoodsDateilVo {
    private String name;
    private String goodsId;
    private Double price;
    private String imgPath;
    private Double seckillPrice;
    private Integer stockNum;
    private Date startTime;
    private Date endTime;
}
