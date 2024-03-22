package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.ShippingDto;
import com.archisacademy.ecommercespringboot.dto.UserDto;
import com.archisacademy.ecommercespringboot.enums.UserRole;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.Shipping;
import com.archisacademy.ecommercespringboot.model.User;
import com.archisacademy.ecommercespringboot.repository.ProductRepository;
import com.archisacademy.ecommercespringboot.repository.ShippingRepository;
import com.archisacademy.ecommercespringboot.repository.UserRepository;
import com.archisacademy.ecommercespringboot.service.ShippingService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ShippingServiceImpl implements ShippingService {

    private final ShippingRepository shippingRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ShippingServiceImpl(ShippingRepository shippingRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.shippingRepository = shippingRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public String createShipping(ShippingDto shippingDto) {
        Shipping shippingForDb = new Shipping();
        shippingForDb.setAddress(shippingDto.getAddress());
        shippingForDb.setShippedAt(shippingDto.getShippedAt());

        /*List<Product> productList = new ArrayList<>();
        for (ProductDto product : shippingDto.getProductList()) {
            Optional<Product> existingProduct = productRepository.findByUuid(product.getUuid());
            if (!existingUser.isPresent()) {
                throw new RuntimeException("Product not found");
            }
            productList.add(existingProduct.get());

        }
        shippingForDb.setProductList(productList);*/


        List<User> userList = new ArrayList<>();
        for (UserDto userDto : shippingDto.getUserList()) {
            Optional<User> existingUser = userRepository.findByUuid(userDto.getUuid());
            if (!existingUser.isPresent()) {
                throw new RuntimeException("User not found");
            }
            userList.add(existingUser.get());

        }
        shippingForDb.setUserList(userList);




        shippingRepository.save(shippingForDb);
        return "Shipping successfully created";
    }

    @Override
    public String updateShipping(Long id, ShippingDto shippingDto) {
        Optional<Shipping> shippingForUpdate = shippingRepository.findById(id);
        if (shippingForUpdate.isEmpty()){
            throw new RuntimeException("Shipping not found");
        }
        Shipping shipping = shippingForUpdate.get();
        shipping.setAddress(shippingDto.getAddress());
        shipping.setShippedAt(shippingDto.getShippedAt());

        /*List<Product> productList = new ArrayList<>();
        for (ProductDto product : shippingDto.getProductList()) {
            Optional<Product> existingProduct = productRepository.findByUuid(product.getUuid());
            if (!existingUser.isPresent()) {
                throw new RuntimeException("Product not found");
            }
            productList.add(existingProduct.get());

        }
        shipping.setProductList(productList);*/


        List<User> userList = new ArrayList<>();
        for (UserDto userDto : shippingDto.getUserList()) {
            Optional<User> existingUser = userRepository.findByUuid(userDto.getUuid());
            if (!existingUser.isPresent()) {
                throw new RuntimeException("User not found");
            }
            userList.add(existingUser.get());

        }

        shipping.setUserList(userList);

        shippingRepository.save(shipping);

        return "Shipping successfully updated";
    }

    @Override
    public void deleteShipping(Long id) {
        getShippingById(id);

        shippingRepository.deleteById(id);
    }

    @Override
    public ShippingDto getShippingById(Long id) {
        Optional<Shipping> shippingOptional = shippingRepository.findById(id);
        if (shippingOptional.isEmpty()){
            throw new RuntimeException("Shipping not found");
        }
        Shipping shipping = shippingOptional.get();

        return new ShippingDto(shipping.getAddress(),
                shipping.getShippedAt(),
                Collections.singletonList(shipping.getProductList()),
                convertUserListToUserDtoList(shipping.getUserList())
        );
    }

    @Override
    public List<ShippingDto> getAllShippings() {
        List<Shipping> shippingList = shippingRepository.findAll();

        return shippingList.stream().map(shipping -> new ShippingDto(
                shipping.getAddress(),
                shipping.getShippedAt(),
                Collections.singletonList(shipping.getProductList()),
                convertUserListToUserDtoList(shipping.getUserList())
        )).toList();
    }

    private List<UserDto> convertUserListToUserDtoList(List<User> userList) { // when product pushed to git , the method will be removed
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            UserDto userDto = new UserDto();
            userDto.setUuid(user.getUuid());
            userDto.setUserName(user.getUserName());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());
            userDto.setTelephone(user.getTelephone());
            userDto.setAddress(user.getAddress());
            userDto.setUserRole(String.valueOf(UserRole.valueOf(user.getUserRole().name())));
            userDto.setCreatedAt(user.getCreatedAt());
            userDto.setUpdatedAt(user.getUpdatedAt());
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
}
