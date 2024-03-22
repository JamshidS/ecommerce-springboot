package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.CartDto;
import com.archisacademy.ecommercespringboot.model.Cart;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.Promotion;
import com.archisacademy.ecommercespringboot.model.User;
import com.archisacademy.ecommercespringboot.repository.*;
import com.archisacademy.ecommercespringboot.service.CartService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final PromotionRepository promotionRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, PromotionRepository promotionRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.promotionRepository = promotionRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public String saveCart(CartDto cartDto) {
        Optional<User> user = userRepository.findByUuid(cartDto.getUserUuid());
        Optional<Promotion> promotion = promotionRepository.findByUuid(cartDto.getPromotionUuid());
        Optional<Product> product = productRepository.findByUuid(cartDto.getProductUuid());
        if(user.isEmpty()){
            throw new RuntimeException("User not found!");
        }
        Cart cart = new Cart();
        cart.setUuid(UUID.randomUUID().toString());
        cart.setOrderDate(new Timestamp(System.currentTimeMillis()));
        cart.setUser(user.get());
        cart.setPromotion(promotion.get());
        cart.setProduct(product.get());
        cartRepository.save(cart);
        return "Cart saved successfully!";
    }

    @Override
    public String updateCart(CartDto cartDto, String cartUuid) {
        Optional<Cart> cart = cartRepository.findByUuid(cartUuid);
        Optional<Promotion> promotion = promotionRepository.findByUuid(cartDto.getPromotionUuid());
        Optional<Product> product = productRepository.findByUuid(cartDto.getProductUuid());
        if(cart.isEmpty()){
            throw new RuntimeException("Cart not found!");
        }
        Cart updatedCart = new Cart();
        updatedCart.setOrderDate(new Timestamp(System.currentTimeMillis()));
        updatedCart.setPromotion(promotion.get());
        updatedCart.setProduct(product.get());
        cartRepository.save(updatedCart);
        return "Cart updated successfully!";
    }

    @Override
    public void deleteCart(String cartUuid) {
        Optional<Cart> cart = cartRepository.findByUuid(cartUuid);
        if(cart.isEmpty()){
            throw new RuntimeException("Cart not found!");
        }
        cartRepository.delete(cart.get());
    }

    @Override
    public CartDto getCartByUuid(String cartUuid) {
        Optional<Cart> cart = cartRepository.findByUuid(cartUuid);
        if(cart.isEmpty()){
            throw new RuntimeException("Cart not found");
        }
        return convertToDto(cart.get());
    }

    @Override
    public CartDto getCartByUserUuid(String userUuid){
        Optional<Cart> cart = cartRepository.findByUserUuid(userUuid);
        if(cart.isEmpty()){
            throw new RuntimeException("Cart not found");
        }
        return convertToDto(cart.get());
    }

    private CartDto convertToDto(Cart cart){
        CartDto cartDto = new CartDto();
        BeanUtils.copyProperties(cart,cartDto);
        return cartDto;
    }
}
