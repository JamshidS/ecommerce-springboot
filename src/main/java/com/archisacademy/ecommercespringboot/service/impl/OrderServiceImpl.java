package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.OrderDto;
import com.archisacademy.ecommercespringboot.enums.OrderStatus;
import com.archisacademy.ecommercespringboot.mapper.OrderMapper;
import com.archisacademy.ecommercespringboot.model.Order;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.User;
import com.archisacademy.ecommercespringboot.model.UserAccount;
import com.archisacademy.ecommercespringboot.repository.OrderRepository;
import com.archisacademy.ecommercespringboot.repository.ProductRepository;
import com.archisacademy.ecommercespringboot.repository.UserAccountRepository;
import com.archisacademy.ecommercespringboot.repository.UserRepository;
import com.archisacademy.ecommercespringboot.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final UserAccountRepository userAccountRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, UserAccountRepository userAccountRepository, ProductRepository productRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.userAccountRepository = userAccountRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional
    public String saveOrder(OrderDto orderDto) {
        Optional<User> user = userRepository.findByUuid(orderDto.getUserUuid());
        List<Product> productList = new ArrayList<>();
        for (String productUuid : orderDto.getProductUuid()) {
            productList.add(productRepository.findByUuid(productUuid).get());
        }
        if (user.isEmpty() || productList.isEmpty()) {
            throw new RuntimeException("An error occurred during the save!");
        }
        Order order = orderMapper.convertToEntity(orderDto);
        order.setUuid(UUID.randomUUID().toString());
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setUser(user.get());
        order.setProductList(productList);
        orderRepository.save(order);

        UserAccount userAccount = new UserAccount();
        userAccount.setUuid(UUID.randomUUID().toString());
        userAccount.setPrice(orderDto.getTotalAmount());
        userAccount.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userAccount.setOrderNumber(generateOrderNumber(order.getId()));
        userAccount.setOrderStatus(OrderStatus.PREPARING);
        userAccount.setUser(user.get());
        userAccount.setProducts(productList);

        userAccountRepository.save(userAccount);

        return "Order and user account saved successfully!";
    }

    @Override
    @Transactional
    public String updateOrder(OrderDto orderDto, String orderUuid) {
        Optional<Order> order = orderRepository.findByUuid(orderUuid);
        Optional<User> user = userRepository.findByUuid(orderDto.getUserUuid());
        List<Product> productList = new ArrayList<>();
        for (String productUuid : orderDto.getProductUuid()) {
            productList.add(productRepository.findByUuid(productUuid).get());
        }
        if (order.isEmpty() || user.isEmpty() || productList.isEmpty()) {
            throw new RuntimeException("An error occurred during the update!");
        }
        builderForUpdate(orderDto, user, productList, order.get(), false);
        return "Order updated successfully!";
    }

    private void builderForUpdate(OrderDto orderDto, Optional<User> user, List<Product> productList, Order updatedOrder, boolean isRequired) {
        if (isRequired)
            updatedOrder.setOrderNumber(generateOrderNumber(updatedOrder.getId()));
        updatedOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));
        updatedOrder.setTotalAmount(orderDto.getTotalAmount());
        updatedOrder.setOrderStatus(orderDto.getOrderStatus());
        updatedOrder.setUser(user.get());
        updatedOrder.setProductList(productList);
        orderRepository.save(updatedOrder);
    }

    @Override
    @Transactional
    public void deleteOrder(String orderUuid) {
        Optional<Order> order = orderRepository.findByUuid(orderUuid);
        if (order.isEmpty()) {
            throw new RuntimeException("Order not found!");
        }
        orderRepository.delete(order.get());
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orderList = orderRepository.findAll();
        for (Order order : orderList) {
            orderDtoList.add(orderMapper.convertToDto(order));
        }
        return orderDtoList;
    }

    @Override
    public OrderDto getOrderByUuid(String orderUuid) {
        Optional<Order> order = orderRepository.findByUuid(orderUuid);
        if (order.isEmpty()) {
            throw new RuntimeException("Order not found");
        }
        return orderMapper.convertToDto(order.get());
    }

    @Override
    public List<OrderDto> getAllOrdersByUserUuid(String userUuid) {
        List<OrderDto> orderList = new ArrayList<>();
        List<Order> orders = orderRepository.findByUserUuid(userUuid);
        if (orders.isEmpty()) {
            throw new RuntimeException("Orders not found for user with UUID: " + userUuid);
        }
        for (Order order : orders) {
            orderList.add(orderMapper.convertToDto(order));
        }
        return orderList;
    }

    private String generateOrderNumber(Long id) {
        String formattedDate = LocalDate.now().toString().replace("-", "");
        return formattedDate + id;
    }
}
