package com.archisacademy.ecommercespringboot.service;

public interface WishlistService {
    void addToWishlist(String userUuid, String productUuid);
    void removeFromWishlist(String userUuid, String productUuid);
}
