package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.response.WishlistResponse;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.User;
import com.archisacademy.ecommercespringboot.model.Wishlist;
import com.archisacademy.ecommercespringboot.repository.ProductRepository;
import com.archisacademy.ecommercespringboot.repository.UserRepository;
import com.archisacademy.ecommercespringboot.repository.WishlistRepository;
import com.archisacademy.ecommercespringboot.service.WishlistService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
    @Transactional
    public String addToWishlist(String userUuid, String productUuid) {
        Optional<User> userOptional = userRepository.findByUuid(userUuid);
        Optional<Product> productOptional = productRepository.findByUuid(productUuid);

        if (userOptional.isEmpty() || productOptional.isEmpty()) {
            throw new RuntimeException("User or product not found");
        }
        User user = userOptional.get();
        Product product = productOptional.get();

        Wishlist wishlist = wishlistRepository.findByUserUuid(userUuid);
        if (wishlist == null) {
            wishlist = new Wishlist();
            wishlist.setProducts(Collections.singletonList(product));
            wishlist.setUuid(user.getUuid());
        } else {
            wishlist.getProducts().add(product);
        }
        wishlistRepository.save(wishlist);
        return "Product added to the Wishlist";
    }

    @Override
    @Transactional
    public String removeFromWishlist(String userUuid, String productUuid) {
        Optional<User> userOptional = userRepository.findByUuid(userUuid);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get();

        Wishlist wishlist = wishlistRepository.findByUserUuid(user.getUuid());
        if (wishlist == null) {
            throw new RuntimeException("Wishlist not found for user");
        }

        List<Product> products = wishlist.getProducts().stream()
                .filter(product -> !product.getUuid().equals(productUuid))
                .collect(Collectors.toList());
        wishlist.setProducts(products);

        wishlistRepository.save(wishlist);
        return "Product removed from the Wishlist";
    }

    @Override
    public WishlistResponse getWishlistByUserUuid(String userUuid) {
        Wishlist wishlist = wishlistRepository.findByUserUuid(userUuid);
        return convertToResponse(wishlist);
    }

    @Override
    public WishlistResponse getWishlistByUuid(String wishlistUuid) {
        Wishlist wishlist = wishlistRepository.findByUuid(wishlistUuid);
        return convertToResponse(wishlist);
    }

    private WishlistResponse convertToResponse(Wishlist wishlist) {
        WishlistResponse response = new WishlistResponse();
        if (wishlist != null) {
            response.setProductList(wishlist.getProducts());
            response.setUserUuid(wishlist.getUuid());
        }
        return response;
    }
}