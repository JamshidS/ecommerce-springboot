package com.archisacademy.ecommercespringboot.mapper;

import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.dto.UserDto;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.User;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    public static ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setUuid(product.getUuid());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setCreatedAt(product.getCreatedAt());
        productDto.setUpdatedAt(product.getUpdatedAt());

        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : product.getUserLists()) {
            userDtoList.add(UserMapper.toUserDto(user));
        }
        productDto.setUserLists(userDtoList);
        return productDto;
    }
    // test

    public static Product toProduct(ProductDto productDto) {
        Product product = new Product();
        product.setUuid(productDto.getUuid());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCreatedAt(productDto.getCreatedAt());
        product.setUpdatedAt(productDto.getUpdatedAt());

        List<User> userList = new ArrayList<>();
        for (UserDto userDto : productDto.getUserLists()) {
            userList.add(UserMapper.toUser(userDto));
        }
        product.setUserLists(userList);
        return product;
    }
}
