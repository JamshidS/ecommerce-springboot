package com.archisacademy.ecommercespringboot.mapper.impl;

import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.dto.PromotionDto;
import com.archisacademy.ecommercespringboot.mapper.ProductMapper;
import com.archisacademy.ecommercespringboot.model.Category;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.Promotion;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class ProductMapperImpl implements ProductMapper {


    @Override
    public ProductDto toProductDto(Product product) {
        if (product == null) {
            return null;
        }


        List<PromotionDto> promotionDtoList = new ArrayList<>();
        for (Promotion promotion : product.getPromotionList()) {
            PromotionDto promotionDto = PromotionDto.builder()
                    .fullName(promotion.getCratedBy())
                    .uuid(promotion.getUuid())
                    .name(promotion.getName())
                    .description(promotion.getDescription())
                    .discount(promotion.getDiscount())
                    .code(promotion.getCode())
                    .daysToAdd(promotion.getExpirationDate().getTime())
                    .productUuid(promotion.getProductList().stream()
                            .map(Product::getUuid)
                            .collect(Collectors.toList()))
                    .build();
            promotionDtoList.add(promotionDto);
        }

        return ProductDto.builder()
                .uuid(product.getUuid())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .categoryUuid(product.getCategory().getUuid())
                .promotionList(promotionDtoList)
                .build(
        );
    }


    @Override
    public Product toProduct(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }
        Product product = new Product();
        product.setName(productDto.getName());
        product.setUuid(productDto.getUuid());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCreatedAt(productDto.getCreatedAt());
        product.setUpdatedAt(productDto.getUpdatedAt());

        Category category = new Category();
        category.setUuid(productDto.getCategoryUuid());
        product.setCategory(category);

        List<Promotion> promotionList = getPromotions(productDto);
        product.setPromotionList(promotionList);

        return product;
    }

    private static List<Promotion> getPromotions(ProductDto productDto) {
        List<Promotion> promotionList = new ArrayList<>();
        for (PromotionDto promotionDto : productDto.getPromotionList()) {

            Promotion promotion = new Promotion();

            promotion.setUuid(promotionDto.getUuid());
            promotion.setName(promotionDto.getName());
            promotion.setDescription(promotionDto.getDescription());
            promotion.setDiscount(promotionDto.getDiscount());
            promotion.setCode(promotionDto.getCode());

            promotionList.add(promotion);
        }
        return promotionList;
    }

    @Override
    public List<ProductDto> toProductDtoList(List<Product> productList) {
        return productList.stream()
                .map(this::toProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> toProductList(List<ProductDto> productDtoList) {
        return productDtoList.stream()
                .map(this::toProduct)
                .collect(Collectors.toList());
    }
}
