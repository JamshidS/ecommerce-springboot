package com.archisacademy.ecommercespringboot.mapper.impl;

import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.dto.PromotionDto;
import com.archisacademy.ecommercespringboot.mapper.ProductMapper;
import com.archisacademy.ecommercespringboot.mapper.PromotionMapper;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.Promotion;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PromotionMapperImpl implements PromotionMapper {
    private final ProductMapper productMapper;

    public PromotionMapperImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public PromotionDto toPromotionDto(Promotion promotion) {
        PromotionDto promotionDto = new PromotionDto();
        promotionDto.setUuid(promotion.getUuid());
        promotionDto.setName(promotion.getName());
        promotionDto.setDescription(promotion.getDescription());
        promotionDto.setDiscount(promotion.getDiscount());
        promotionDto.setCode(promotion.getCode());

        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : promotion.getProductList()) {
            productDtoList.add(productMapper.toProductDto(product));
        }
        promotionDto.setProductList(productDtoList);
        return promotionDto;
    }

    @Override
    public Promotion toPromotion(PromotionDto promotionDto) {
        Promotion promotion = new Promotion();
        promotion.setUuid(promotionDto.getUuid());
        promotion.setName(promotionDto.getName());
        promotion.setDescription(promotionDto.getDescription());
        promotion.setDiscount(promotionDto.getDiscount());
        promotion.setCode(promotionDto.getCode());

        List<Product> productList = new ArrayList<>();
        for (ProductDto productDto : promotionDto.getProductList()) {
            productList.add(productMapper.toProduct(productDto));
        }
        promotion.setProductList(productList);
        return promotion;
    }

    @Override
    public List<PromotionDto> toPromotionDtoList(List<Promotion> promotionList) {
        List<PromotionDto> promotionDtoList = new ArrayList<>();

        if (promotionList != null) {
            return promotionList.stream()
                    .map(this::toPromotionDto)
                    .collect(Collectors.toList());
        }

        return promotionDtoList;
    }
}
