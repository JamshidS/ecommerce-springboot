package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.OrderDto;
import com.archisacademy.ecommercespringboot.dto.UserDto;
import com.archisacademy.ecommercespringboot.model.Order;
import com.archisacademy.ecommercespringboot.model.User;
import com.archisacademy.ecommercespringboot.repository.OrderRepository;
import com.archisacademy.ecommercespringboot.repository.UserRepository;
import com.archisacademy.ecommercespringboot.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public String saveOrder(OrderDto orderDto) {
        Optional<User> user = userRepository.findByUuid(orderDto.getUserUuid());
        Order order = new Order();
        order.setUuid(orderDto.getUuid());
        order.setOrderNumber(orderDto.getOrderNumber());
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setUser(user.get());
        orderRepository.save(order);
        return "Order saved successfully!";
    }

    @Override
    public String updateOrder(OrderDto orderDto, String orderUuid) {
        Optional<Order> order = orderRepository.findByUuid(orderUuid);
        Optional<User> user = userRepository.findByUuid(orderDto.getUserUuid());
        if(order.isEmpty() || user.isEmpty()){
            throw new RuntimeException("An error occurred during the update!");
        }
        Order updatedOrder = new Order();
        updatedOrder.setOrderNumber(orderDto.getOrderNumber());
        updatedOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));
        updatedOrder.setTotalAmount(orderDto.getTotalAmount());
        updatedOrder.setOrderStatus(orderDto.getOrderStatus());
        updatedOrder.setUser(user.get());
        orderRepository.save(updatedOrder);
        return "Order updated successfully!";
    }

    @Override
    public void deleteOrder(String orderUuid) {
        Optional<Order> order = orderRepository.findByUuid(orderUuid);
        if(order.isEmpty()){
            throw new RuntimeException("Order not found!");
        }
        orderRepository.delete(order.get());
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orderList = orderRepository.findAll();
        for(Order order : orderList){
            orderDtoList.add(convertToDto(order));
        }
        return orderDtoList;
    }

    @Override
    public OrderDto getOrderByUuid(String orderUuid) {
        Optional<Order> order = orderRepository.findByUuid(orderUuid);
        if(order.isEmpty()){
            throw new RuntimeException("Order not found");
        }
        return convertToDto(order.get());
    }

    @Override
    public List<OrderDto> getAllOrdersByUserUuid(String userUuid) {
        List<OrderDto> orderList = new ArrayList<>();
        List<Order> orders = orderRepository.findByUserUuid(userUuid);
        if(orders.isEmpty()) {
            throw new RuntimeException("Orders not found for user with UUID: " + userUuid);
        }
        for (Order order : orders) {
            orderList.add(convertToDto(order));
        }
        return orderList;
    }

    private OrderDto convertToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(order, orderDto);
        return orderDto;
    }
}
