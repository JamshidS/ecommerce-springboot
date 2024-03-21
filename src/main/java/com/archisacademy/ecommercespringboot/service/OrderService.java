package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.OrderDto;

import java.util.List;

public interface OrderService {
    String saveOrder(OrderDto orderDto);
    String updateOrder(OrderDto orderDto, String orderUuid);
    void deleteOrder(String orderUuid);
    List<OrderDto> getAllOrders();
    OrderDto getOrderByUuid(String orderUuid);
    List<OrderDto> getAllOrdersByUserUuid(String userUuid);
}
