package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.CartDto;
import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.dto.response.CartResponse;
import com.archisacademy.ecommercespringboot.model.*;
import com.archisacademy.ecommercespringboot.repository.*;
import com.archisacademy.ecommercespringboot.service.CartService;
import com.archisacademy.ecommercespringboot.service.ProductService;
import com.archisacademy.ecommercespringboot.utils.CommonUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
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
    private final PromotionRepository promotionRepository;
    private final ProductService productService;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, PromotionRepository promotionRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.promotionRepository = promotionRepository;
        this.productService = productService;
    }

    @Override
    @Transactional
    public CartResponse saveCart(CartDto cartDto) {
        Optional<User> user = userRepository.findByUuid(cartDto.getUserUuid());
        Promotion promotion = promotionRepository.findByUuid(cartDto.getPromotionUuid());
        if(user.isEmpty()){
            throw new RuntimeException("User not found!");
        }
        Cart cart = new Cart();
        cart.setUuid(UUID.randomUUID().toString());
        cart.setOrderDate(new Timestamp(System.currentTimeMillis()));
        cart.setUser(user.get());
        cart.setPromotion(promotion);
        cart.setProductUUIDs(CommonUtils.arrayToCommaSeparatedString(cartDto.getProductUuids()));
        cartRepository.save(cart);
        return createResponse(cart);
    }

    @Override
    @Transactional
    public CartResponse updateCart(CartDto cartDto, String cartUuid) {
        Optional<Cart> cart = cartRepository.findByUuid(cartUuid);
        Promotion promotion = promotionRepository.findByUuid(cartDto.getPromotionUuid());
        if(cart.isEmpty()){
            throw new RuntimeException("Cart not found!");
        }
        cart.get().setPromotion(promotion);
        cart.get().setProductUUIDs(CommonUtils.arrayToCommaSeparatedString(cartDto.getProductUuids()));
        cartRepository.save(cart.get());
        return createResponse(cart.get());
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
        String[] uuids = CommonUtils.commaSeparatedStringToArray(cart.getProductUUIDs());
        List<ProductDto> productDtoList = new ArrayList<>();
        for(String uuid : uuids){
            productDtoList.add(productService.getProductByUuid(uuid));
        }
        CartDto cartDto = new CartDto();
        BeanUtils.copyProperties(cart,cartDto);
        cartDto.setProductUuids(uuids);
        cartDto.setProductDtoList(productDtoList);
        return cartDto;
    }

    private CartResponse createResponse(Cart cart){
        CartResponse cartResponse = new CartResponse();
        if (cart != null) {
            String[] uuids = CommonUtils.commaSeparatedStringToArray(cart.getProductUUIDs());
            double actualAmount = 0.0;
            for(String uuid : uuids){
                actualAmount += productService.getProductByUuid(uuid).getPrice();
            }
            double discount = cart.getPromotion().getDiscount();
            cartResponse.setTotalActualAmount(actualAmount);
            cartResponse.setPromotionAmount(discount);
            cartResponse.setTotalAmountAfterPromotion(actualAmount-discount);
        }
        return cartResponse;
    }
}
