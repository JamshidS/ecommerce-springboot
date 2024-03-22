package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.ReturnDto;
import com.archisacademy.ecommercespringboot.model.*;
import com.archisacademy.ecommercespringboot.repository.*;
import com.archisacademy.ecommercespringboot.service.ReturnService;

import java.util.List;
import java.util.Optional;

public class ReturnServiceImpl implements ReturnService {

    private final ReturnRepository returnRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public ReturnServiceImpl(ReturnRepository returnRepository, UserRepository userRepository, CartRepository cartRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.returnRepository = returnRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public String createReturn(ReturnDto returnDto) {
        Return returnForDb = new Return();
        returnForDb.setAddress(returnDto.getAddress());
        returnForDb.setReason(returnDto.getReason());
        returnForDb.setReturnDate(returnDto.getReturnDate());

        returnForDb.setUser(userRepository.findByUuid(returnDto.getUserUuid()).orElseThrow(() -> new RuntimeException("User not found")));
        returnForDb.setProduct(productRepository.findByUuid(returnDto.getProductUuid()).orElseThrow(() -> new RuntimeException("Product not found")));
        returnForDb.setCart(cartRepository.findById(returnDto.getCartId()).orElseThrow(() -> new RuntimeException("Cart not found")));
        returnForDb.setOrder(orderRepository.findById(returnDto.getOrderId()).orElseThrow(() -> new RuntimeException("Order not found")));

        returnRepository.save(returnForDb);
        return "Return successfully created";
    }

    @Override
    public String updateReturn(ReturnDto returnDto) {
        Return returnForUpdate = new Return();
        returnForUpdate.setAddress(returnDto.getAddress());
        returnForUpdate.setReason(returnDto.getReason());
        returnForUpdate.setReturnDate(returnDto.getReturnDate());
        returnForUpdate.setUser(userRepository.findByUuid(returnDto.getUserUuid()).orElseThrow(() -> new RuntimeException("User not found")));
        returnForUpdate.setProduct(productRepository.findByUuid(returnDto.getProductUuid()).orElseThrow(() -> new RuntimeException("Product not found")));
        returnForUpdate.setCart(cartRepository.findById(returnDto.getCartId()).orElseThrow(() -> new RuntimeException("Cart not found")));
        returnForUpdate.setOrder(orderRepository.findById(returnDto.getOrderId()).orElseThrow(() -> new RuntimeException("Order not found")));

        returnRepository.save(returnForUpdate);

        return "Return successfully updated";
    }

    @Override
    public List<ReturnDto> getAllReturns() {
        List<Return> returnList = returnRepository.findAll();

        return returnList.stream().map(returnObj -> new ReturnDto(
                returnObj.getAddress(),
                returnObj.getReason(),
                returnObj.getReturnDate(),
                returnObj.getUser().getUuid(),
                returnObj.getProduct().getUuid(),
                returnObj.getCart().getId(),
                returnObj.getOrder().getId()
        )).toList();
    }

    @Override
    public ReturnDto getReturnById(Long returnId) {
        Optional<Return> returnOptional = returnRepository.findById(returnId);
        if (returnOptional.isEmpty()) {
            throw new RuntimeException("Return not found");
        }
        Return returnObj = returnOptional.get();
        return new ReturnDto(
                returnObj.getAddress(),
                returnObj.getReason(),
                returnObj.getReturnDate(),
                returnObj.getUser().getUuid(),
                returnObj.getProduct().getUuid(),
                returnObj.getCart().getId(),
                returnObj.getOrder().getId()
        );
    }

    @Override
    public void deleteReturn(Long returnId) {
        Optional<Return> returnOptional = returnRepository.findById(returnId);
        if (returnOptional.isEmpty()) {
            throw new RuntimeException("Return not found");
        }
        returnRepository.delete(returnOptional.get());

    }
}
