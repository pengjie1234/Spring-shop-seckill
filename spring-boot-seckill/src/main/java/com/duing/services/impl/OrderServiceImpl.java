package com.duing.services.impl;

import com.duing.mapper.OrderMapper;
import com.duing.model.Order;

import com.duing.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void addOrder(Order order) {
         orderMapper.addOrder(order);
    }
}
