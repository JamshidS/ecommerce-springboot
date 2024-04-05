package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.CartDto;
import com.archisacademy.ecommercespringboot.dto.response.CartResponse;

public interface CartService {
    CartResponse saveCart(CartDto cartDto);
    CartResponse updateCart(CartDto cartDto, String cartUuid);
    void deleteCart(String cartUuid);
    CartDto getCartByUuid(String cartUuid);
    CartDto getCartByUserUuid(String userUuid);

}
