package com.duing.mapper;

import com.duing.model.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    void addOrder(Order order);
}
