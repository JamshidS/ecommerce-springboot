package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.CartDto;
import com.archisacademy.ecommercespringboot.dto.response.CartResponse;
import com.archisacademy.ecommercespringboot.model.Cart;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.Promotion;
import com.archisacademy.ecommercespringboot.model.User;
import com.archisacademy.ecommercespringboot.repository.*;
import com.archisacademy.ecommercespringboot.service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
    public CartResponse saveCart(CartDto cartDto) {
        Optional<User> user = userRepository.findByUuid(cartDto.getUserUuid());
        Promotion promotion = promotionRepository.findByUuid(cartDto.getPromotionUuid());
        Optional<Product> product = productRepository.findByUuid(cartDto.getProductUuid());
        if(user.isEmpty()){
            throw new RuntimeException("User not found!");
        }
        Cart cart = new Cart();
        cart.setUuid(UUID.randomUUID().toString());
        cart.setOrderDate(new Timestamp(System.currentTimeMillis()));
        cart.setUser(user.get());
        cart.setPromotion(promotion);
        cart.setProduct(product.get());
        cartRepository.save(cart);
        return createResponse(cart);
    }

    @Override
    @Transactional
    public CartResponse updateCart(CartDto cartDto, String cartUuid) {
        Optional<Cart> cart = cartRepository.findByUuid(cartUuid);
        Promotion promotion = promotionRepository.findByUuid(cartDto.getPromotionUuid());
        Optional<Product> product = productRepository.findByUuid(cartDto.getProductUuid());
        if(cart.isEmpty()){
            throw new RuntimeException("Cart not found!");
        }
        Cart updatedCart = new Cart();
        updatedCart.setOrderDate(new Timestamp(System.currentTimeMillis()));
        updatedCart.setPromotion(promotion);
        updatedCart.setProduct(product.get());
        cartRepository.save(updatedCart);
        return createResponse(updatedCart);
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

    private CartResponse createResponse(Cart cart){
        CartResponse cartResponse = new CartResponse();
        double actualAmount = cart.getProduct().getPrice();
        double discount = cart.getPromotion().getDiscount();
        cartResponse.setTotalActualAmount(actualAmount);
        cartResponse.setPromotionAmount(discount);
        cartResponse.setTotalAmountAfterPromotion(actualAmount-discount);
        return cartResponse;
    }
}
