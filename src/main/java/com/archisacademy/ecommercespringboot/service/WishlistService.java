package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.WishlistDto;

import java.util.List;

public interface WishlistService {
    String addToWishlist(String userUuid, String productUuid);

    String removeFromWishlist(String userUuid, String productUuid);

    List<WishlistDto> getAllWishlistsByUser(String userUuid);

    WishlistDto getWishlistById(String wishlistUuid);

}
