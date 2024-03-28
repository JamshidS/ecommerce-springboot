package com.archisacademy.ecommercespringboot.mapper.impl;

import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.dto.ShippingDto;
import com.archisacademy.ecommercespringboot.dto.UserDto;
import com.archisacademy.ecommercespringboot.enums.UserRole;
import com.archisacademy.ecommercespringboot.mapper.ShippingMapper;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.Shipping;
import com.archisacademy.ecommercespringboot.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShippingMapperImpl implements ShippingMapper {
    @Override
    public ShippingDto toDto(Shipping shipping) {
        if (shipping == null) {
            return null;
        }
        ShippingDto shippingDto = new ShippingDto();
        shippingDto.setAddress(shipping.getAddress());
        shippingDto.setShippedAt(shipping.getShippedAt());

        if (shipping.getProductList() != null){
            List<ProductDto> productDtoList = new ArrayList<>();
            for (Product product : shipping.getProductList()) {
                ProductDto productDto = new ProductDto();
                productDto.setUuid(product.getUuid());
                productDto.setName(product.getName());
                productDto.setDescription(product.getDescription());
                productDto.setPrice(product.getPrice());
                productDto.setCreatedAt(product.getCreatedAt());
                productDto.setUpdatedAt(product.getUpdatedAt());

                productDtoList.add(productDto);
            }
            shippingDto.setProductList(productDtoList);
        }

        if (shipping.getUserList() != null){
            List<UserDto> userDtoList = new ArrayList<>();
            for (User user : shipping.getUserList()) {
                UserDto userDto = new UserDto();
                userDto.setUuid(user.getUuid());
                userDto.setUserName(user.getUserName());
                userDto.setEmail(user.getEmail());
                userDto.setCreatedAt(user.getCreatedAt());
                userDto.setUpdatedAt(user.getUpdatedAt());
                userDto.setAddress(user.getAddress());
                userDto.setTelephone(user.getTelephone());

                userDtoList.add(userDto);
            }
            shippingDto.setUserList(userDtoList);
        }
        return shippingDto;
    }

    @Override
    public Shipping toEntity(ShippingDto shippingDto) {
        if (shippingDto == null) {
            return null;
        }
        Shipping shipping = new Shipping();
        shipping.setAddress(shippingDto.getAddress());
        shipping.setShippedAt(shippingDto.getShippedAt());

        if (shippingDto.getProductList() != null){
            List<Product> productList = new ArrayList<>();
            for (ProductDto productDto : shippingDto.getProductList()) {
                Product product = new Product();
                product.setUuid(productDto.getUuid());
                product.setName(productDto.getName());
                product.setDescription(productDto.getDescription());
                product.setPrice(productDto.getPrice());
                product.setCreatedAt(productDto.getCreatedAt());
                product.setUpdatedAt(productDto.getUpdatedAt());

                productList.add(product);
            }
            shipping.setProductList(productList);
        }

        if (shippingDto.getUserList() != null){
            List<User> userList = new ArrayList<>();
            for (UserDto userDto : shippingDto.getUserList()) {
                User user = new User();
                user.setUuid(userDto.getUuid());
                user.setUserName(userDto.getUserName());
                user.setEmail(userDto.getEmail());
                user.setCreatedAt(userDto.getCreatedAt());
                user.setUpdatedAt(userDto.getUpdatedAt());
                user.setAddress(userDto.getAddress());
                user.setTelephone(userDto.getTelephone());

                userList.add(user);
            }
            shipping.setUserList(userList);
        }
        return shipping;
    }

    @Override
    public List<ShippingDto> toDtoList(List<Shipping> shippingList) {
        if (shippingList == null) {
            return null;
        }
        List<ShippingDto> shippingDtoList = new ArrayList<>();
        for (Shipping shipping : shippingList) {
            shippingDtoList.add(toDto(shipping));
        }
        return shippingDtoList;
    }
}
