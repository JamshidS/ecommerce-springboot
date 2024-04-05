package com.archisacademy.ecommercespringboot.mapper;

import com.archisacademy.ecommercespringboot.dto.CartDto;
import com.archisacademy.ecommercespringboot.dto.response.CartResponse;
import com.archisacademy.ecommercespringboot.model.Cart;

public interface CartMapper {
    CartDto convertToDto(Cart cart);

    CartResponse createResponse(Cart cart);

}
