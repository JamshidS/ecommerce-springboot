package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.CategoryDto;
import com.archisacademy.ecommercespringboot.model.Category;
import com.archisacademy.ecommercespringboot.repository.CategoryRepository;
import com.archisacademy.ecommercespringboot.service.CategoryService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        categoryForDb.setProductList(categoryDto.getProductList());

        categoryRepository.save(categoryForDb);

        return "Category successfully created";
    }

    @Override
    public String updateCategory(CategoryDto categoryDto) {
        Optional<Category> categoryForUpdate = categoryRepository.findByUUID(categoryDto.getUuid());
        if (categoryForUpdate.isEmpty()){
        throw new RuntimeException("Category not found");
        }
        else {
            Category category = categoryForUpdate.get();
            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());
            category.setCreatedAt(categoryDto.getCreatedAt());
            category.setUpdatedAt(categoryDto.getUpdatedAt());
            category.setProductList(categoryDto.getProductList());

            categoryRepository.save(category);

            return "Category successfully updated";
        }
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(category -> {
            CategoryDto categoryDto = new CategoryDto();

            categoryDto.setName(category.getName());
            categoryDto.setUuid(category.getUuid());
            categoryDto.setDescription(category.getDescription());
            categoryDto.setCreatedAt(category.getCreatedAt());
            categoryDto.setUpdatedAt(category.getUpdatedAt());
            categoryDto.setProductList(category.getProductList());

            return categoryDto;
        }).toList();
    }

    @Override
    public CategoryDto getCategoryByUuid(String categoryUuid) {
        Optional<Category> categoryOptional = categoryRepository.findByUUID(categoryUuid);
        if (categoryOptional.isEmpty()){
            throw new RuntimeException("Category not found");
        }
        else {
            Category category = categoryOptional.get();

            CategoryDto categoryDto = new CategoryDto(
                    category.getName(),
                    category.getUuid(),
                    category.getDescription(),
                    category.getCreatedAt(),
                    category.getUpdatedAt(),
                    category.getProductList()
            );

            return categoryDto;
        }
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()){
            throw new RuntimeException("Category not found");
        }
        else {
            categoryRepository.deleteById(categoryId);
            return "Category successfully deleted";
        }
    }
}
