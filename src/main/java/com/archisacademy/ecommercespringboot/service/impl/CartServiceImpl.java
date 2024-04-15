package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.CartDto;
import com.archisacademy.ecommercespringboot.dto.response.CartResponse;
import com.archisacademy.ecommercespringboot.mapper.CartMapper;
import com.archisacademy.ecommercespringboot.model.*;
import com.archisacademy.ecommercespringboot.repository.*;
import com.archisacademy.ecommercespringboot.service.CartService;
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
    private final CartMapper cartMapper;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, PromotionRepository promotionRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.promotionRepository = promotionRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    @Transactional
    public CartResponse saveCart(CartDto cartDto) {
        Optional<User> user = userRepository.findByUuid(cartDto.getUserUuid());
        Optional<Promotion> promotionOptional = promotionRepository.findByUuid(cartDto.getPromotionUuid());

        if (user.isEmpty()) {
            throw new RuntimeException("User not found!");
        }

        Promotion promotion = promotionOptional.orElse(null);

        Cart cart = new Cart();
        cart.setUuid(UUID.randomUUID().toString());
        cart.setOrderDate(new Timestamp(System.currentTimeMillis()));
        cart.setUser(user.get());
        cart.setPromotion(promotion);
        cart.setProductUUIDs(CommonUtils.arrayToCommaSeparatedString(cartDto.getProductUuids())); //todo: we should consider checking the quantity of the product
        cartRepository.save(cart);
        return cartMapper.createResponse(cart);
    }

    @Override
    @Transactional
    public CartResponse updateCart(CartDto cartDto, String cartUuid) {
        Optional<Cart> cartOptional = cartRepository.findByUuid(cartUuid);
        Optional<Promotion> promotionOptional = promotionRepository.findByUuid(cartDto.getPromotionUuid());

        if (cartOptional.isEmpty()) {
            throw new RuntimeException("Cart not found!");
        }

        Cart cart = cartOptional.get();
        Promotion promotion = promotionOptional.orElse(null);

        cart.setPromotion(promotion);
        cart.setProductUUIDs(CommonUtils.arrayToCommaSeparatedString(cartDto.getProductUuids()));
        cartRepository.save(cart);
        return cartMapper.createResponse(cart);
    }

    @Override
    public void deleteCart(String cartUuid) {
        Optional<Cart> cartOptional = cartRepository.findByUuid(cartUuid);
        if (cartOptional.isEmpty()) {
            throw new RuntimeException("Cart not found!");
        }
        cartRepository.delete(cartOptional.get());
    }

    @Override
    public CartDto getCartByUuid(String cartUuid) {
        Optional<Cart> cartOptional = cartRepository.findByUuid(cartUuid);
        if (cartOptional.isEmpty()) {
            throw new RuntimeException("Cart not found");
        }
        return cartMapper.convertToDto(cartOptional.get());
    }

    @Override
    public CartDto getCartByUserUuid(String userUuid) {
        Optional<Cart> cartOptional = cartRepository.findByUserUuid(userUuid);
        if (cartOptional.isEmpty()) {
            throw new RuntimeException("Cart not found");
        }
        return cartMapper.convertToDto(cartOptional.get());
    }
}
