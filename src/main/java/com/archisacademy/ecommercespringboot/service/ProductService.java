package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.ProductDto;

import java.util.List;

public interface ProductService {
    public String createProduct(ProductDto productDto);
    public String updateProduct(ProductDto productDto);
    public ProductDto getProductByUuid(String uuid);
    public List<ProductDto> getAllProducts();
    public void deleteProductByUuid(String uuid);
    public List<ProductDto> getProductsByCategory(String categoryUuid);
    public List<ProductDto> getProductsByUser(String userUuid);
    public List<ProductDto> getProductsByPromotion(String promotionUuid);
    public List<ProductDto> getProductsByPrice(double price);
    public List<ProductDto> getProductsByPriceRange(double minPrice, double maxPrice);

}
