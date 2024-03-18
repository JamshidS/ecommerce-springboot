package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.WishlistDto;
import com.archisacademy.ecommercespringboot.dto.response.WishlistResponse;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.User;
import com.archisacademy.ecommercespringboot.model.Wishlist;
import com.archisacademy.ecommercespringboot.repository.ProductRepository;
import com.archisacademy.ecommercespringboot.repository.UserRepository;
import com.archisacademy.ecommercespringboot.repository.WishlistRepository;
import com.archisacademy.ecommercespringboot.service.WishlistService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;


@Service
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public String addToWishlist(String userUuid, String productUuid) {
        Optional<User> userOptional = userRepository.findByUuid(userUuid.trim());
        Optional<Product> productOptional = productRepository.findByUuid(productUuid.trim());

        if (!userOptional.isPresent() || !productOptional.isPresent()) {
            throw new RuntimeException("User or product not found");
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(userOptional.get());
        wishlist.setProducts(Collections.singletonList(productOptional.get()));
        wishlistRepository.save(wishlist);
        return "Product added to the Wishlist";

    }

    @Override
    public String removeFromWishlist(String userUuid, String productUuid) {
        Optional<Wishlist> wishlistOptional = wishlistRepository.findByUserUuidAndProductUuid(userUuid.trim(), productUuid.trim());

        if (!wishlistOptional.isPresent()) {
            throw new RuntimeException("Wishlist item not found");
        }

        wishlistRepository.delete(wishlistOptional.get());
        return "Product removed from the Wishlist";
    }

    @Override
    public WishlistResponse getWishlistByUserUuid(String userUuid) {
        return null;
    }

    @Override
    public WishlistResponse getWishlistByUuid(String wishlistUuid) {
        return null;
    }

//response degistir
    private WishlistDto convertToResponse(Wishlist wishlist) {
        WishlistDto dto = new WishlistDto();
        dto.setUuid(wishlist.getUuid());
        dto.setUserUuid(wishlist.getUser().getUuid());
        dto.setProductUuid(wishlist.getProducts().get(0).getUuid());
        return dto;
    }
}