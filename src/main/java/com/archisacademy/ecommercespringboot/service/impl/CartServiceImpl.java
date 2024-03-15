package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.CartDto;
import com.archisacademy.ecommercespringboot.model.Cart;
import com.archisacademy.ecommercespringboot.model.Promotion;
import com.archisacademy.ecommercespringboot.model.User;
import com.archisacademy.ecommercespringboot.repository.*;
import com.archisacademy.ecommercespringboot.service.CartService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final PromotionRepository promotionRepository;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, PromotionRepository promotionRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.promotionRepository = promotionRepository;
    }

    @Override
    public void saveCart(CartDto cartDto) {
        Optional<User> user = userRepository.findByUuid(cartDto.getUserUuid().trim());
        Optional<Promotion> promotion = promotionRepository.findByUuid(cartDto.getPromotionUuid());
        Cart cart = new Cart();
        cart.setUuid(UUID.randomUUID().toString());
        cart.setOrderDate(cartDto.getOrder_date());
        cart.setUser(user.get());
        cart.setPromotion(promotion.get());
        cartRepository.save(cart);
    }

    @Override
    public void updateCart(CartDto cartDto) {

    }

    @Override
    public void deleteCart(Long cartId) {

    }

    @Override
    public List<CartDto> getAllCarts() {
        return null;
    }

    @Override
    public CartDto getCartByUuid(String cartUuid) {
        return null;
    }


}
