package com.archisacademy.ecommercespringboot.mapper;

import com.archisacademy.ecommercespringboot.dto.response.WishlistResponse;
import com.archisacademy.ecommercespringboot.model.Wishlist;

public interface WishlistMapper {
    WishlistResponse convertToResponse(Wishlist wishlist);

}
