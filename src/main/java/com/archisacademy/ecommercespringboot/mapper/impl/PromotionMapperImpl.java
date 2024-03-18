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
        if (promotion == null) {
            throw new IllegalArgumentException("Null promotion can not be mapped to promotionDto");
        }

        return new PromotionDto(
                promotion.getUuid(),
                promotion.getName(),
                promotion.getDescription(),
                promotion.getDiscount(),
                promotion.getCode(),
                productMapper.toProductDtoList(promotion.getProductList())
        );
    }

    @Override
    public Promotion toPromotion(PromotionDto promotionDto) {
        if (promotionDto == null) {
            throw new IllegalArgumentException("Null promotionDto can not be mapped to promotion");
        }
        Promotion promotion = new Promotion();
        promotion.setUuid(promotionDto.getUuid());
        promotion.setName(promotionDto.getName());
        promotion.setDescription(promotionDto.getDescription());
        promotion.setDiscount(promotionDto.getDiscount());
        promotion.setCode(promotionDto.getCode());

        promotion.setProductList(productMapper.toProductList(promotionDto.getProductList()));
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
