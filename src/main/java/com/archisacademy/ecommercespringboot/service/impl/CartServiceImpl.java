package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.CartDto;
import com.archisacademy.ecommercespringboot.dto.response.CartResponse;
import com.archisacademy.ecommercespringboot.mapper.CartMapper;
import com.archisacademy.ecommercespringboot.model.*;
import com.archisacademy.ecommercespringboot.repository.*;
import com.archisacademy.ecommercespringboot.service.CartService;
import com.archisacademy.ecommercespringboot.service.ProductService;
import com.archisacademy.ecommercespringboot.utils.CommonUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final PromotionRepository promotionRepository;
    private final ProductService productService; //todo: remove this because no one is using it
    private final CartMapper cartMapper;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, PromotionRepository promotionRepository, ProductService productService, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.promotionRepository = promotionRepository;
        this.productService = productService;
        this.cartMapper = cartMapper;
    }

    @Override
    @Transactional
    public CartResponse saveCart(CartDto cartDto) {
        Optional<User> user = userRepository.findByUuid(cartDto.getUserUuid());
        Promotion promotion = promotionRepository.findByUuid(cartDto.getPromotionUuid()); //todo: this needs to be optional and check if it is empty or not
        if (user.isEmpty()) {
            throw new RuntimeException("User not found!");
        }
        Cart cart = new Cart();
        cart.setUuid(UUID.randomUUID().toString());
        cart.setOrderDate(new Timestamp(System.currentTimeMillis()));
        cart.setUser(user.get());
        cart.setPromotion(promotion);
        cart.setProductUUIDs(CommonUtils.arrayToCommaSeparatedString(cartDto.getProductUuids()));
        cartRepository.save(cart);
        return cartMapper.createResponse(cart);
    }

    @Override
    @Transactional
    public CartResponse updateCart(CartDto cartDto, String cartUuid) {
        Optional<Cart> cart = cartRepository.findByUuid(cartUuid);
        Promotion promotion = promotionRepository.findByUuid(cartDto.getPromotionUuid());
        if (cart.isEmpty()) {
            throw new RuntimeException("Cart not found!");
        }
        cart.get().setPromotion(promotion);
        cart.get().setProductUUIDs(CommonUtils.arrayToCommaSeparatedString(cartDto.getProductUuids()));
        cartRepository.save(cart.get());
        return cartMapper.createResponse(cart.get());
    }

    @Override
    public void deleteCart(String cartUuid) {
        Optional<Cart> cart = cartRepository.findByUuid(cartUuid);
        if (cart.isEmpty()) {
            throw new RuntimeException("Cart not found!");
        }
        cartRepository.delete(cart.get());
    }

    @Override
    public CartDto getCartByUuid(String cartUuid) {
        Optional<Cart> cart = cartRepository.findByUuid(cartUuid);
        if (cart.isEmpty()) {
            throw new RuntimeException("Cart not found");
        }
        return cartMapper.convertToDto(cart.get());
    }

    @Override
    public CartDto getCartByUserUuid(String userUuid) {
        Optional<Cart> cart = cartRepository.findByUserUuid(userUuid);
        if (cart.isEmpty()) {
            throw new RuntimeException("Cart not found");
        }
        return cartMapper.convertToDto(cart.get());
    }
}
