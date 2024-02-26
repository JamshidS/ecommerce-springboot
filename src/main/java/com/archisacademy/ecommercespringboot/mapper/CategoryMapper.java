package com.archisacademy.ecommercespringboot.mapper;

import com.archisacademy.ecommercespringboot.dto.CategoryDto;
import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.model.Category;
import com.archisacademy.ecommercespringboot.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {
    public static CategoryDto toCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setUuid(category.getUuid());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setCreatedAt(category.getCreatedAt());
        categoryDto.setUpdatedAt(category.getUpdatedAt());
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : category.getProductList()) {
            productDtoList.add(ProductMapper.toProductDto(product));
        }
        categoryDto.setProductList(productDtoList);
        return categoryDto;

    }
    public static Category toCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setUuid(categoryDto.getUuid());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setCreatedAt(categoryDto.getCreatedAt());
        category.setUpdatedAt(categoryDto.getUpdatedAt());
        List<Product> productList = new ArrayList<>();
        for (ProductDto productDto : categoryDto.getProductList()) {
            productList.add(ProductMapper.toProduct(productDto));
        }
        category.setProductList(productList);
        return category;
    }
}
