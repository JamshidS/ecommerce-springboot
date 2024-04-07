package com.archisacademy.ecommercespringboot.mapper;

import com.archisacademy.ecommercespringboot.dto.CategoryDto;
import com.archisacademy.ecommercespringboot.model.Category;

public interface CategoryMapper {
    CategoryDto toCategoryDto(Category category);

    Category toCategory(CategoryDto categoryDto);
}
