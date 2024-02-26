package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.dto.PromotionDto;
import com.archisacademy.ecommercespringboot.dto.UserDto;
import com.archisacademy.ecommercespringboot.enums.UserRole;
import com.archisacademy.ecommercespringboot.mapper.CategoryMapper;
import com.archisacademy.ecommercespringboot.mapper.PromotionMapper;
import com.archisacademy.ecommercespringboot.mapper.UserMapper;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.Promotion;
import com.archisacademy.ecommercespringboot.model.User;
import com.archisacademy.ecommercespringboot.repository.ProductRepository;
import com.archisacademy.ecommercespringboot.service.CategoryService;
import com.archisacademy.ecommercespringboot.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;


    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public String createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setUuid(UUID.randomUUID().toString());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCreatedAt(productDto.getCreatedAt());
        product.setUpdatedAt(productDto.getUpdatedAt());
        product.setCategory(CategoryMapper.toCategory(categoryService.getCategoryByUuid(productDto.getCategoryUuid())));

        List<User> users = new ArrayList<>();
        for (UserDto userDto : productDto.getUserLists()) {
            users.add(UserMapper.toUser(userDto));
        }

        product.setUserLists(users);

        List<Promotion> promotions = new ArrayList<>();
        for (PromotionDto promotion : productDto.getPromotionList()) {
            promotions.add(PromotionMapper.toPromotion(promotion));
        }

        product.setPromotionList(promotions);

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
        product.setCategory(CategoryMapper.toCategory(categoryService.getCategoryByUuid(productDto.getCategoryUuid())));

        List<User> users = new ArrayList<>();
        for (UserDto userDto : productDto.getUserLists()) {
            users.add(UserMapper.toUser(userDto));
        }

        product.setUserLists(users);

        List<Promotion> promotions = new ArrayList<>();
        for (PromotionDto promotion : productDto.getPromotionList()) {
            promotions.add(PromotionMapper.toPromotion(promotion));
        }

        product.setPromotionList(promotions);


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
                Collections.singletonList((UserDto) product.getUserLists()),
                Collections.singletonList((PromotionDto) product.getPromotionList())
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
                product.getUserLists().stream().map(UserMapper::toUserDto).collect(Collectors.toList()),
                product.getPromotionList().stream().map(PromotionMapper::toPromotionDto).collect(Collectors.toList())
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
        return products.stream().filter(Objects::nonNull).map(product -> new ProductDto(
                product.getName(),
                product.getUuid(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getCategory().getUuid(),
                product.getUserLists().stream().map(UserMapper::toUserDto).collect(Collectors.toList()),
                product.getPromotionList().stream().map(PromotionMapper::toPromotionDto).collect(Collectors.toList())
        )).toList();
    }

    @Override
    public List<ProductDto> getProductsByUser(String userUuid) {
        List<Product> products = productRepository.findByUserUuid(userUuid);
        return products.stream().filter(Objects::nonNull).map(product -> new ProductDto(
                product.getName(),
                product.getUuid(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getCategory().getUuid(),
                product.getUserLists().stream().map(UserMapper::toUserDto).collect(Collectors.toList()),
                product.getPromotionList().stream().map(PromotionMapper::toPromotionDto).collect(Collectors.toList())
        )).toList();
    }

    @Override
    public List<ProductDto> getProductsByPromotion(String promotionUuid) {
        List<Product> products = productRepository.findByPromotionUuid(promotionUuid);
        return products.stream().filter(Objects::nonNull).map(product -> new ProductDto(
                product.getName(),
                product.getUuid(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getCategory().getUuid(),
                product.getUserLists().stream().map(UserMapper::toUserDto).collect(Collectors.toList()),
                product.getPromotionList().stream().map(PromotionMapper::toPromotionDto).collect(Collectors.toList())
        )).toList();
    }

    @Override
    public List<ProductDto> getProductsByPrice(double price) {
        List<Product> products = productRepository.findByPrice(price);
        return products.stream().filter(Objects::nonNull).map(product -> new ProductDto(
                product.getName(),
                product.getUuid(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getCategory().getUuid(),
                product.getUserLists().stream().map(UserMapper::toUserDto).collect(Collectors.toList()),
                product.getPromotionList().stream().map(PromotionMapper::toPromotionDto).collect(Collectors.toList())
        )).toList();
    }

    @Override
    public List<ProductDto> getProductsByPriceRange(double minPrice, double maxPrice) {
        List<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice);
        return products.stream().filter(Objects::nonNull).map(product -> new ProductDto(
                product.getName(),
                product.getUuid(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getCategory().getUuid(),
                product.getUserLists().stream().map(UserMapper::toUserDto).collect(Collectors.toList()),
                product.getPromotionList().stream().map(PromotionMapper::toPromotionDto).collect(Collectors.toList())
        )).toList();
    }

}
