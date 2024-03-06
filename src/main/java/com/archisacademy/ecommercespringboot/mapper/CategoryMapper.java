package com.archisacademy.ecommercespringboot.mapper;

import com.archisacademy.ecommercespringboot.dto.CategoryDto;
import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.model.Category;
import com.archisacademy.ecommercespringboot.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public interface CategoryMapper {
    CategoryDto toCategoryDto(Category category);
    Category toCategory(CategoryDto categoryDto);
}
