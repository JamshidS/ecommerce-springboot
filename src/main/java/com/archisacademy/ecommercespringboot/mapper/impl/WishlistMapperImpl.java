package com.archisacademy.ecommercespringboot.mapper.impl;

import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.dto.response.WishlistResponse;
import com.archisacademy.ecommercespringboot.mapper.ProductMapper;
import com.archisacademy.ecommercespringboot.mapper.WishlistMapper;
import com.archisacademy.ecommercespringboot.model.Wishlist;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WishlistMapperImpl implements WishlistMapper {
    private final ProductMapper productMapper; // ProductMapper injection

    public WishlistMapperImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public WishlistResponse convertToResponse(Wishlist wishlist) {
        WishlistResponse response = new WishlistResponse();
        if (wishlist != null) {
            List<ProductDto> productDTOs = wishlist.getProducts()
                    .stream()
                    .map(productMapper::toProductDto)
                    .collect(Collectors.toList());
            response.setProductList(productDTOs);
            response.setUserUuid(wishlist.getUuid());
        }
        return response;
    }
}