package com.archisacademy.ecommercespringboot.controller;

import com.archisacademy.ecommercespringboot.dto.OrderDto;
import com.archisacademy.ecommercespringboot.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> saveOrder(@RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderService.saveOrder(orderDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{orderUuid}")
    public ResponseEntity<String> updateOrder(@RequestBody OrderDto orderDto, @PathVariable String orderUuid) {
        return ResponseEntity.ok(orderService.updateOrder(orderDto, orderUuid));
    }

    @DeleteMapping("/delete/{orderUuid}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String orderUuid) {
        orderService.deleteOrder(orderUuid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{orderUuid}")
    public ResponseEntity<OrderDto> getOrderByUuid(@PathVariable String orderUuid) {
        return ResponseEntity.ok(orderService.getOrderByUuid(orderUuid));
    }

    @GetMapping("/user/{userUuid}")
    public ResponseEntity<List<OrderDto>> getAllOrdersByUserUuid(@PathVariable String userUuid) {
        return ResponseEntity.ok(orderService.getAllOrdersByUserUuid(userUuid));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
