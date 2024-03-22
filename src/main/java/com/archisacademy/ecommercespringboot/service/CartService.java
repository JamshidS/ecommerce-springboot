package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.CartDto;

import java.util.List;

public interface CartService {
    String saveCart(CartDto cartDto);
    String updateCart(CartDto cartDto, String cartUuid);
    void deleteCart(String cartUuid);
    CartDto getCartByUuid(String cartUuid);
    CartDto getCartByUserUuid(String userUuid);

}
