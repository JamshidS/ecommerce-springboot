package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.WishlistDto;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.User;
import com.archisacademy.ecommercespringboot.model.Wishlist;
import com.archisacademy.ecommercespringboot.repository.ProductRepository;
import com.archisacademy.ecommercespringboot.repository.UserRepository;
import com.archisacademy.ecommercespringboot.repository.WishlistRepository;
import com.archisacademy.ecommercespringboot.service.WishlistService;
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
    public String addToWishlist(String userUuid, String productUuid) {
        Optional<User> userOptional = userRepository.findByUuid(userUuid.trim());
        Optional<Product> productOptional = productRepository.findByUuid(productUuid.trim());

        if (!userOptional.isPresent() || !productOptional.isPresent()) {
            throw new RuntimeException("User or product not found");
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setUsers(Collections.singletonList(userOptional.get()));
        wishlist.setProducts(Collections.singletonList(productOptional.get()));
        wishlistRepository.save(wishlist);
        throw new RuntimeException("whislist is added");

    }

    @Override
    public String removeFromWishlist(String userUuid, String productUuid) {
        Optional<Wishlist> wishlistOptional = wishlistRepository.findByUserUuidAndProductUuid(userUuid.trim(), productUuid.trim());

        if (!wishlistOptional.isPresent()) {
            throw new RuntimeException("Wishlist item not found");
        }

        wishlistRepository.delete(wishlistOptional.get());
        throw new RuntimeException("whislist is removed");
    }

    @Override
    public List<WishlistDto> getAllWishlistsByUser(String userUuid) {
        return wishlistRepository.findAllByUserUuid(userUuid).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public WishlistDto getWishlistById(String wishlistUuid) {
        Optional<Wishlist> wishlistOptional = Optional.ofNullable(wishlistRepository.findByUuid(wishlistUuid));
        if (wishlistOptional.isPresent()) {
            return convertToDto(wishlistOptional.get());
        } else {
            throw new RuntimeException("Wishlist not found");
        }
    }

    private WishlistDto convertToDto(Wishlist wishlist) {
        WishlistDto dto = new WishlistDto();
        dto.setUuid(wishlist.getUuid());
        dto.setUserUuid(wishlist.getUsers().get(0).getUuid());
        dto.setProductUuid(wishlist.getProducts().get(0).getUuid());
        return dto;
    }
}