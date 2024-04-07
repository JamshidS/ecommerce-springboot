package com.archisacademy.ecommercespringboot.mapper;

import com.archisacademy.ecommercespringboot.dto.OrderDto;
import com.archisacademy.ecommercespringboot.model.Order;

public interface OrderMapper {
    OrderDto convertToDto(Order order);

}
