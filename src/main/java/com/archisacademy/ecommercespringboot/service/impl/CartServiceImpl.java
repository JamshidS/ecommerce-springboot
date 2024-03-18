package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.CartDto;
import com.archisacademy.ecommercespringboot.model.Cart;
import com.archisacademy.ecommercespringboot.model.Promotion;
import com.archisacademy.ecommercespringboot.model.User;
import com.archisacademy.ecommercespringboot.repository.*;
import com.archisacademy.ecommercespringboot.service.CartService;
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

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, PromotionRepository promotionRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.promotionRepository = promotionRepository;
    }

    @Override
    public String saveCart(CartDto cartDto) {
        Optional<User> user = userRepository.findByUuid(cartDto.getUserUuid());
        Optional<Promotion> promotion = promotionRepository.findByUuid(cartDto.getPromotionUuid());
        Cart cart = new Cart();
        cart.setUuid(UUID.randomUUID().toString());
        cart.setOrderDate(Timestamp.valueOf(cartDto.getOrder_date().toString()));
        cart.setUser(user.get());
        cart.setPromotion(promotion.get());
        cartRepository.save(cart);
        return "Cart saved successfully!";
    }

    @Override
    public String updateCart(CartDto cartDto, String cartUuid) {
        Optional<Cart> cart = cartRepository.findByUuid(cartUuid);
        Optional<User> user = userRepository.findByUuid(cartDto.getUserUuid());
        Optional<Promotion> promotion = promotionRepository.findByUuid(cartDto.getPromotionUuid());
        if(cart.isEmpty() || user.isEmpty() || promotion.isEmpty()){
            throw new RuntimeException("An error occurred during the update!");
        }
        cart.ifPresent(updatedCart->{
            updatedCart.setOrderDate(Timestamp.valueOf(cartDto.getOrder_date().toString()));
            updatedCart.setUser(user.get());
            updatedCart.setPromotion(promotion.get());
            cartRepository.save(updatedCart);
        });
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
    public List<CartDto> getAllCarts() {
        List<Cart> cartList = cartRepository.findAll();
        return cartList.stream().map(cart -> new CartDto(
                cart.getUuid(),
                cart.getOrderDate().toLocalDateTime().toLocalDate(),
                cart.getUser().getUuid(),
                cart.getPromotion().getUuid()
        )).toList();
    }

    @Override
    public CartDto getCartByUuid(String cartUuid) {
        Optional<Cart> cart = cartRepository.findByUuid(cartUuid);
        if(cart.isEmpty()){
            throw new RuntimeException("Cart not found");
        }
        return new CartDto(cart.get().getUuid(), cart.get().getOrderDate().toLocalDateTime().toLocalDate(), cart.get().getUser().getUuid(), cart.get().getPromotion().getUuid());
    }

    @Override
    public CartDto getCartByUserUuid(String userUuid){
        Optional<Cart> cart = cartRepository.findByUserUuid(userUuid);
        if(cart.isEmpty()){
            throw new RuntimeException("Cart not found");
        }
        return new CartDto(cart.get().getUuid(), cart.get().getOrderDate().toLocalDateTime().toLocalDate(), cart.get().getUser().getUuid(), cart.get().getPromotion().getUuid());
    }

}
