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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductService productService;
    private final InventoryRepository inventoryRepository;
    private final PromotionRepository promotionRepository;
    private final CartMapper cartMapper;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, ProductService productService, InventoryRepository inventoryRepository, PromotionRepository promotionRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
        this.inventoryRepository = inventoryRepository;
        this.promotionRepository = promotionRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    @Transactional
    public CartResponse saveCart(CartDto cartDto) {
        Optional<User> user = userRepository.findByUuid(cartDto.getUserUuid());
        Optional<Promotion> promotionOptional = promotionRepository.findByUuid(cartDto.getPromotionCode());

        if (user.isEmpty()) {
            throw new RuntimeException("User not found!");
        }

        Promotion promotion = promotionOptional.orElse(null);

        Cart cart = new Cart();
        cart.setUuid(UUID.randomUUID().toString());
        cart.setOrderDate(new Timestamp(System.currentTimeMillis()));
        cart.setUser(user.get());
        cart.setPromotion(promotion);

        // Check product quantities and add product UUIDs to the cart
        List<String> productUuids = new ArrayList<>();
        for (String productUuid : cartDto.getProductUuids()) {
            Optional<Inventory> inventoryOptional = inventoryRepository.findByReferenceCode(productUuid);
            if (inventoryOptional.isEmpty() || inventoryOptional.get().getQuantity() <= 0) {
                throw new RuntimeException("Product quantity not available for product with UUID: " + productUuid);
            }
            // Assuming each product is added once
            productUuids.add(productUuid);

            // Update inventory
            Inventory inventory = inventoryOptional.get();
            inventory.setQuantity(inventory.getQuantity() - 1);
            inventoryRepository.save(inventory);
        }

        cart.setProductUUIDs(CommonUtils.arrayToCommaSeparatedString(productUuids.toArray(productUuids.toArray(new String[0]))));
        cartRepository.save(cart);
        return cartMapper.createResponse(cart);
    }

    @Override
    @Transactional
    public CartResponse updateCart(CartDto cartDto, String cartUuid) {
        Optional<Cart> cartOptional = cartRepository.findByUuid(cartUuid);
        Optional<Promotion> promotionOptional = promotionRepository.findByUuid(cartDto.getPromotionCode());

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
    @Transactional
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
