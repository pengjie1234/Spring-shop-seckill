package com.duing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private Integer id;
    private String goods_id;
    private String goods_name;
    private String goods_type;
    private Double price;
    private String img_path;
}
