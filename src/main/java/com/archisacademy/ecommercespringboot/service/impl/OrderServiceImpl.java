package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.OrderDto;
import com.archisacademy.ecommercespringboot.model.Order;
import com.archisacademy.ecommercespringboot.model.User;
import com.archisacademy.ecommercespringboot.repository.OrderRepository;
import com.archisacademy.ecommercespringboot.repository.UserRepository;
import com.archisacademy.ecommercespringboot.service.OrderService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
    public String saveOrder(OrderDto orderDto) {
        Optional<User> user = userRepository.findByUuid(orderDto.getUserUuid());
        Order order = new Order();
        order.setUuid(orderDto.getUuid());
        order.setOrderNumber(orderDto.getOrderNumber());
        order.setOrderDate(Timestamp.valueOf(orderDto.getOrderDate().toString()));
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
        order.ifPresent(updatedOrder->{
            updatedOrder.setOrderNumber(orderDto.getOrderNumber());
            updatedOrder.setOrderDate(Timestamp.valueOf(orderDto.getOrderDate().toString()));
            updatedOrder.setTotalAmount(orderDto.getTotalAmount());
            updatedOrder.setOrderStatus(orderDto.getOrderStatus());
            updatedOrder.setUser(user.get());
            orderRepository.save(updatedOrder);
        });
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
        List<Order> orderList = orderRepository.findAll();
        return orderList.stream().map(order -> new OrderDto(
                order.getUuid(),
                order.getOrderNumber(),
                order.getOrderDate().toLocalDateTime().toLocalDate(),
                order.getTotalAmount(),
                order.getOrderStatus(),
                order.getUser().getUuid()
        )).toList();
    }

    @Override
    public OrderDto getOrderByUuid(String orderUuid) {
        Optional<Order> order = orderRepository.findByUuid(orderUuid);
        if(order.isEmpty()){
            throw new RuntimeException("Order not found");
        }
        return new OrderDto(
                order.get().getUuid(),
                order.get().getOrderNumber(),
                order.get().getOrderDate().toLocalDateTime().toLocalDate(),
                order.get().getTotalAmount(),
                order.get().getOrderStatus(),
                order.get().getUser().getUuid()
        );
    }

    @Override
    public OrderDto getOrderByUserUuid(String userUuid) {
        Optional<Order> order = orderRepository.findByUserUuid(userUuid);
        if(order.isEmpty()){
            throw new RuntimeException("Order not found");
        }
        return new OrderDto(
                order.get().getUuid(),
                order.get().getOrderNumber(),
                order.get().getOrderDate().toLocalDateTime().toLocalDate(),
                order.get().getTotalAmount(),
                order.get().getOrderStatus(),
                order.get().getUser().getUuid()
        );
    }
}
