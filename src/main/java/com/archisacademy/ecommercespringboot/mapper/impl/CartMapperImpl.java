package com.archisacademy.ecommercespringboot.mapper.impl;

import com.archisacademy.ecommercespringboot.dto.CartDto;
import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.dto.response.CartResponse;
import com.archisacademy.ecommercespringboot.mapper.CartMapper;
import com.archisacademy.ecommercespringboot.model.Cart;
import com.archisacademy.ecommercespringboot.service.ProductService;
import com.archisacademy.ecommercespringboot.utils.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartMapperImpl implements CartMapper {
    private final ProductService productService;

    public CartMapperImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public CartDto convertToDto(Cart cart) {
        String[] uuids = CommonUtils.commaSeparatedStringToArray(cart.getProductUUIDs());
        List<ProductDto> productDtoList = new ArrayList<>();
        for (String uuid : uuids) {
            productDtoList.add(productService.getProductByUuid(uuid));
        }
        CartDto cartDto = new CartDto();
        BeanUtils.copyProperties(cart, cartDto);
        cartDto.setProductUuids(uuids);
        cartDto.setProductDtoList(productDtoList);
        return cartDto;
    }

    @Override
    public CartResponse createResponse(Cart cart) {
        CartResponse cartResponse = new CartResponse();
        if (cart != null) {
            String[] uuids = CommonUtils.commaSeparatedStringToArray(cart.getProductUUIDs());
            double actualAmount = 0.0;
            for (String uuid : uuids) {
                actualAmount += productService.getProductByUuid(uuid).getPrice();
            }
            double discount = cart.getPromotion().getDiscount();
            cartResponse.setTotalActualAmount(actualAmount);
            cartResponse.setPromotionAmount(discount);
            cartResponse.setTotalAmountAfterPromotion(actualAmount - discount);
        }
        return cartResponse;
    }
}
