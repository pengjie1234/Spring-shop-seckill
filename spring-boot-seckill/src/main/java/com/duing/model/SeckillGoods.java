package com.duing.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeckillGoods {
    private Integer id;
    private String goods_id;
    private Double seckill_price;
    private Integer stock_num;
    private Date start_time;
    private Date end_time;

}
