package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.CategoryDto;
import com.archisacademy.ecommercespringboot.model.Category;
import com.archisacademy.ecommercespringboot.repository.CategoryRepository;
import com.archisacademy.ecommercespringboot.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String createCategory(CategoryDto categoryDto) {
        Category categoryForDb = new Category();
        categoryForDb.setName(categoryDto.getName());
        categoryForDb.setUuid(UUID.randomUUID().toString());
        categoryForDb.setDescription(categoryDto.getDescription());
        categoryForDb.setCreatedAt(categoryDto.getCreatedAt());
        categoryForDb.setUpdatedAt(categoryDto.getUpdatedAt());

        categoryRepository.save(categoryForDb);

        return "Category successfully created";
    }

    @Override
    public String updateCategory(CategoryDto categoryDto) {
        Optional<Category> categoryForUpdate = categoryRepository.findByUuid(categoryDto.getUuid());
        if (categoryForUpdate.isEmpty()) {
            throw new RuntimeException("Category not found");
        }

        Category category = categoryForUpdate.get();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setCreatedAt(categoryDto.getCreatedAt());
        category.setUpdatedAt(categoryDto.getUpdatedAt());

        categoryRepository.save(category);

        return "Category successfully updated";

    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();

        return categoryList.stream().map(category -> new CategoryDto(
                category.getName(),
                category.getUuid(),
                category.getDescription(),
                category.getCreatedAt(),
                category.getUpdatedAt(),
                null
        )).toList();

    }

    @Override
    public CategoryDto getCategoryByUuid(String categoryUuid) {
        Optional<Category> categoryOptional = categoryRepository.findByUuid(categoryUuid);
        if (categoryOptional.isEmpty()) {
            throw new RuntimeException("Category not found");
        }

        Category category = categoryOptional.get();

        return new CategoryDto(
                category.getName(),
                category.getUuid(),
                category.getDescription(),
                category.getCreatedAt(),
                category.getUpdatedAt(),
                Collections.singletonList(category.getProductList())
        );

    }

    @Override
    public void deleteCategory(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) {
            throw new RuntimeException("Category not found");
        }

        categoryRepository.deleteById(categoryId);

    }
}