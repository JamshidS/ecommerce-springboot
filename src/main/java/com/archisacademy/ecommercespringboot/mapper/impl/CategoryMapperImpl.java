package com.archisacademy.ecommercespringboot.mapper.impl;

import com.archisacademy.ecommercespringboot.dto.CategoryDto;
import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.mapper.CategoryMapper;
import com.archisacademy.ecommercespringboot.mapper.ProductMapper;
import com.archisacademy.ecommercespringboot.model.Category;
import com.archisacademy.ecommercespringboot.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    private final ProductMapper productMapper;

    public CategoryMapperImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }
    @Override
    public CategoryDto toCategoryDto(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Null category can not be mapped to categoryDto");
        }
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setUuid(category.getUuid());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setCreatedAt(category.getCreatedAt());
        categoryDto.setUpdatedAt(category.getUpdatedAt());
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : category.getProductList()) {
            productDtoList.add(productMapper.toProductDto(product));
        }
        categoryDto.setProductList(productDtoList);

        return categoryDto;
    }

    @Override
    public Category toCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setUuid(categoryDto.getUuid());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setCreatedAt(categoryDto.getCreatedAt());
        category.setUpdatedAt(categoryDto.getUpdatedAt());
        List<Product> productList = new ArrayList<>();
        for (ProductDto productDto : categoryDto.getProductList()) {
            productList.add(productMapper.toProduct(productDto));
        }
        category.setProductList(productList);

        return category;
    }
}
