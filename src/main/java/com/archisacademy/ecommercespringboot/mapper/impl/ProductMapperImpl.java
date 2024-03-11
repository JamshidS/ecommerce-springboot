package com.archisacademy.ecommercespringboot.mapper.impl;

import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.mapper.ProductMapper;
import com.archisacademy.ecommercespringboot.mapper.PromotionMapper;
import com.archisacademy.ecommercespringboot.mapper.UserMapper;
import com.archisacademy.ecommercespringboot.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ProductMapperImpl implements ProductMapper {
    private final UserMapper userMapper;
    private final PromotionMapper promotionMapper;

    public ProductMapperImpl(UserMapper userMapper, PromotionMapper promotionMapper) {
        this.userMapper = userMapper;
        this.promotionMapper = promotionMapper;
    }

    @Override
    public ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setUuid(product.getUuid());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setCreatedAt(product.getCreatedAt());
        productDto.setUpdatedAt(product.getUpdatedAt());
        productDto.setCategoryUuid(product.getCategory().getUuid());

        return productDto;
    }

    @Override
    public Product toProduct(ProductDto productDto) {
        Product product = new Product();
        product.setUuid(productDto.getUuid());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCreatedAt(productDto.getCreatedAt());
        product.setUpdatedAt(productDto.getUpdatedAt());

        return product;
    }

    @Override
    public List<ProductDto> toProductDtoList(List<Product> productList) {
        List<ProductDto> productDtoList = new ArrayList<>();

        if (productList != null) {
            return productList.stream()
                    .filter(Objects::nonNull)
                    .map(product -> new ProductDto(
                            product.getName(),
                            product.getUuid(),
                            product.getDescription(),
                            product.getPrice(),
                            product.getCreatedAt(),
                            product.getUpdatedAt(),
                            product.getCategory().getUuid(),
                            userMapper.toUserDtoList(product.getUserLists()),
                            promotionMapper.toPromotionDtoList(product.getPromotionList())
                    ))
                    .collect(Collectors.toList());
        }

        return productDtoList;
    }
}
