package com.archisacademy.ecommercespringboot.service.impl;


import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.dto.PromotionDto;
import com.archisacademy.ecommercespringboot.dto.UserDto;
import com.archisacademy.ecommercespringboot.mapper.PromotionMapper;
import com.archisacademy.ecommercespringboot.mapper.UserMapper;
import com.archisacademy.ecommercespringboot.model.Category;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.Promotion;
import com.archisacademy.ecommercespringboot.model.User;
import com.archisacademy.ecommercespringboot.repository.CategoryRepository;
import com.archisacademy.ecommercespringboot.repository.ProductRepository;
import com.archisacademy.ecommercespringboot.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PromotionMapper promotionMapper;
    private final UserMapper userMapper;


    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, PromotionMapper promotionMapper, UserMapper userMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.promotionMapper = promotionMapper;
        this.userMapper = userMapper;
    }

    @Override
    public String createProduct(ProductDto productDto) {
        Optional<Category> category = categoryRepository.findByUuid(productDto.getCategoryUuid());
        if(category.isEmpty()){
            throw new RuntimeException("Category not found with this UUID: "+productDto.getCategoryUuid());
        }
        Product product = new Product();
        product.setName(productDto.getName());
        product.setUuid(UUID.randomUUID().toString());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCreatedAt(LocalDate.now());
        product.setCategory(category.get());

        productRepository.save(product);

        return "Product created successfully";
    }

    @Override
    public String  updateProduct(ProductDto productDto) {
        Optional<Product> productForUpdate = Optional.ofNullable(productRepository.findByUuid(productDto.getUuid()));

        if (productForUpdate.isEmpty()){
            throw new RuntimeException("Product not found");
        }

        Product product = productForUpdate.get();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCreatedAt(productDto.getCreatedAt());
        product.setUpdatedAt(productDto.getUpdatedAt());

        Optional<Category> category = categoryRepository.findByUuid(productDto.getCategoryUuid());

        if(category.isEmpty()){
            throw new RuntimeException("Category not found with this UUID: "+productDto.getCategoryUuid());
        }

        product.setCategory(category.get());

        productRepository.save(product);

        return "Product updated successfully";

    }

    @Override
    public ProductDto getProductByUuid(String uuid) {
        Optional<Product> productForDb = Optional.ofNullable(productRepository.findByUuid(uuid));
        if (productForDb.isEmpty()){
            throw new RuntimeException("Product not found");
        }
        Product product = productForDb.get();

        return new ProductDto(
                product.getName(),
                product.getUuid(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getCategory().getUuid(),
                product.getUserLists().stream().map(userMapper::toUserDto).collect(Collectors.toList()),
                product.getPromotionList().stream().map(promotionMapper::toPromotionDto).collect(Collectors.toList())
        );
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().filter(Objects::nonNull).map(product -> new ProductDto(
                product.getName(),
                product.getUuid(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getCategory().getUuid(),
                product.getUserLists().stream().map(userMapper::toUserDto).collect(Collectors.toList()),
                product.getPromotionList().stream().map(promotionMapper::toPromotionDto).collect(Collectors.toList())
        )).toList();
    }

    @Override
    public void deleteProductById(Long id) {
        Optional<Product> productForDeletion = productRepository.findById(id);
        if (productForDeletion.isEmpty()){
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> getProductsByCategory(String categoryUuid) {
        List<Product> products = productRepository.findByCategoryUuid(categoryUuid);
        return mapToProductDtoList(products);
    }

    @Override
    public List<ProductDto> getProductsByUser(String userUuid) {
        List<Product> products = productRepository.findByUserUuid(userUuid);
        return mapToProductDtoList(products);
    }

    @Override
    public List<ProductDto> getProductsByPromotion(String promotionUuid) {
        List<Product> products = productRepository.findByPromotionUuid(promotionUuid);
        return mapToProductDtoList(products);
    }

    @Override
    public List<ProductDto> getProductsByPrice(double price) {
        List<Product> products = productRepository.findByPrice(price);
        return mapToProductDtoList(products);
    }

    @Override
    public List<ProductDto> getProductsByPriceRange(double minPrice, double maxPrice) {
        List<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice);
        return mapToProductDtoList(products);
    }

    private List<ProductDto> mapToProductDtoList(List<Product> productList) {
        if (productList == null) {
            return null;
        }

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
                        mapToUserDtoList(product.getUserLists()),
                        mapToPromotionDtoList(product.getPromotionList())
                ))
                .collect(Collectors.toList());
    }

    private List<UserDto> mapToUserDtoList(List<User> userList) {
        if (userList == null) {
            return null;
        }

        return userList.stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    private List<PromotionDto> mapToPromotionDtoList(List<Promotion> promotionList) {
        if (promotionList == null) {
            return null;
        }

        return promotionList.stream()
                .map(promotionMapper::toPromotionDto)
                .collect(Collectors.toList());
    }

}
