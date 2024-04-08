package com.archisacademy.ecommercespringboot.mapper.impl;

import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.dto.PromotionDto;
import com.archisacademy.ecommercespringboot.mapper.PromotionMapper;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.Promotion;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PromotionMapperImpl implements PromotionMapper {

    @Override
    public PromotionDto toPromotionDto(Promotion promotion) {
        if (promotion == null) {
            throw new IllegalArgumentException("Null promotion can not be mapped to promotionDto");
        }

        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : promotion.getProductList()) {
            ProductDto productDto = new ProductDto();
            productDto.setUuid(product.getUuid());
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            productDtoList.add(productDto);
        }

        return new PromotionDto(
                promotion.getUuid(),
                promotion.getName(),
                promotion.getDescription(),
                promotion.getDiscount(),
                promotion.getCode(),
                productDtoList
        );
    }

    @Override
    public Promotion toPromotion(PromotionDto promotionDto) {
        if (promotionDto == null) {
            throw new IllegalArgumentException("Null promotionDto can not be mapped to promotion");
        }

        List<Product> productList = new ArrayList<>();
        for (ProductDto productDto : promotionDto.getProductList()) {
            Product product = new Product();
            product.setUuid(productDto.getUuid());
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            productList.add(product);
        }

        Promotion promotion = new Promotion();
        promotion.setUuid(promotionDto.getUuid());
        promotion.setName(promotionDto.getName());
        promotion.setDescription(promotionDto.getDescription());
        promotion.setDiscount(promotionDto.getDiscount());
        promotion.setCode(promotionDto.getCode());

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
