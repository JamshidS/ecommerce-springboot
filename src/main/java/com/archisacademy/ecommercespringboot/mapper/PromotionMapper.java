package com.archisacademy.ecommercespringboot.mapper;

import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.dto.PromotionDto;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.Promotion;

import java.util.ArrayList;
import java.util.List;

public class PromotionMapper {
    public static PromotionDto toPromotionDto(Promotion promotion) {
        PromotionDto promotionDto = new PromotionDto();
        promotionDto.setUuid(promotion.getUuid());
        promotionDto.setName(promotion.getName());
        promotionDto.setDescription(promotion.getDescription());
        promotionDto.setDiscount(promotion.getDiscount());
        promotionDto.setCode(promotion.getCode());

        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : promotion.getProductList()) {
            productDtoList.add(ProductMapper.toProductDto(product));
        }
        promotionDto.setProductList(productDtoList);
        return promotionDto;
    }

    public static Promotion toPromotion(PromotionDto promotionDto) {
        Promotion promotion = new Promotion();
        promotion.setUuid(promotionDto.getUuid());
        promotion.setName(promotionDto.getName());
        promotion.setDescription(promotionDto.getDescription());
        promotion.setDiscount(promotionDto.getDiscount());
        promotion.setCode(promotionDto.getCode());

        List<Product> productList = new ArrayList<>();
        for (ProductDto productDto : promotionDto.getProductList()) {
            productList.add(ProductMapper.toProduct(productDto));
        }
        promotion.setProductList(productList);
        return promotion;
    }
}
