package com.archisacademy.ecommercespringboot.mapper.impl;

import com.archisacademy.ecommercespringboot.dto.response.WishlistResponse;
import com.archisacademy.ecommercespringboot.mapper.WishlistMapper;
import com.archisacademy.ecommercespringboot.model.Wishlist;
import org.springframework.stereotype.Component;

@Component
public class WishlistMapperImpl implements WishlistMapper {
    @Override
    public WishlistResponse convertToResponse(Wishlist wishlist) {
        WishlistResponse response = new WishlistResponse();
        if (wishlist != null) {
            response.setProductList(wishlist.getProducts());
            response.setUserUuid(wishlist.getUuid());
        }
        return response;
    }
}
