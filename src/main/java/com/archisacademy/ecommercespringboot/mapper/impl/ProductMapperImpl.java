package com.archisacademy.ecommercespringboot.mapper.impl;

import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.mapper.CategoryMapper;
import com.archisacademy.ecommercespringboot.mapper.ProductMapper;
import com.archisacademy.ecommercespringboot.mapper.PromotionMapper;
import com.archisacademy.ecommercespringboot.mapper.UserMapper;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.service.CategoryService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ProductMapperImpl implements ProductMapper {


    @Override
    public ProductDto toProductDto(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductDto(
                product.getName(),
                product.getUuid(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getCategory().getUuid(),
                null // PromotionList burada atanmadı
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
        // Category ve PromotionList burada atanmadı
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
