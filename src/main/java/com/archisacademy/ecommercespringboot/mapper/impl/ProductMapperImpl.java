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
            PromotionDto promotionDto = new PromotionDto(
                    promotion.getUuid(),
                    promotion.getName(),
                    promotion.getDescription(),
                    promotion.getDiscount(),
                    promotion.getCode(),
                    null
            );
            promotionDtoList.add(promotionDto);
        }

        return new ProductDto(
                product.getName(),
                product.getUuid(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getCategory().getUuid(),
                promotionDtoList
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
        product.setPromotionList(promotionList);

        return product;
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
