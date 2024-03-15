package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.CartDto;

import java.util.List;

public interface CartService {
    void saveCart(CartDto cartDto);
    void updateCart(CartDto cartDto);
    void deleteCart(Long cartId);
    List<CartDto> getAllCarts();
    CartDto getCartByUuid(String cartUuid);

}
