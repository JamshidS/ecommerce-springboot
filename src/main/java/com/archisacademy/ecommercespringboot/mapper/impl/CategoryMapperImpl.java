package com.archisacademy.ecommercespringboot.mapper.impl;

import com.archisacademy.ecommercespringboot.dto.CategoryDto;
import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.mapper.CategoryMapper;
import com.archisacademy.ecommercespringboot.mapper.ProductMapper;
import com.archisacademy.ecommercespringboot.model.Category;
import com.archisacademy.ecommercespringboot.model.Product;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapperImpl implements CategoryMapper {

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
            ProductDto productDto = new ProductDto();
            productDto.setUuid(product.getUuid());
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            productDtoList.add(productDto);
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
        category.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        category.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        List<Product> productList = new ArrayList<>();
        for (ProductDto productDto : categoryDto.getProductList()) {
            Product product = new Product();
            product.setUuid(productDto.getUuid());
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            productList.add(product);
        }
        category.setProductList(productList);

        return category;
    }
}
