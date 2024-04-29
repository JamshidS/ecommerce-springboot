package com.archisacademy.ecommercespringboot.mapper.impl;

import com.archisacademy.ecommercespringboot.dto.OrderDto;
import com.archisacademy.ecommercespringboot.mapper.OrderMapper;
import com.archisacademy.ecommercespringboot.model.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper {
    @Override
    public OrderDto convertToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(order, orderDto);
        return orderDto;
    }
    @Override
    public Order convertToEntity(OrderDto orderDto) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDto, order);
        return order;
    }
}
