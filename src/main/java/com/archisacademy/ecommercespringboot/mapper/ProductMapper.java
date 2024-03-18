package com.archisacademy.ecommercespringboot.mapper;

import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.dto.UserDto;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.User;

import java.util.ArrayList;
import java.util.List;

public interface ProductMapper {
    ProductDto toProductDto(Product product);
    Product toProduct(ProductDto productDto);
    List<ProductDto> toProductDtoList(List<Product> productList);
    List<Product> toProductList(List<ProductDto> productDtoList);
}
