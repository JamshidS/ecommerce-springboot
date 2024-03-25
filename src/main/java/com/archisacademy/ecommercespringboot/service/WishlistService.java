package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.response.WishlistResponse;


public interface WishlistService {
    String addToWishlist(String userUuid, String productUuid);

    String removeFromWishlist(String userUuid, String productUuid);

    WishlistResponse getWishlistByUserUuid(String userUuid);

    WishlistResponse getWishlistByUuid(String wishlistUuid);

}
