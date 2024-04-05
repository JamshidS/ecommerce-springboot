package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.CartDto;
import com.archisacademy.ecommercespringboot.dto.OrderDto;
import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.dto.UserDto;
import com.archisacademy.ecommercespringboot.dto.response.WishlistResponse;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserByUuid(String uuid);

    void saveUser(UserDto userDto);

    void updateUser(String uuid, UserDto updatedUserDto);

    void deleteUser(String uuid);

    void updateWishlistForUser (String userUuid, List<String> productUuids);

    List<ProductDto> getAllProductsByUserUuid(String userUuid);

    WishlistResponse getWishlistByUserUUID(String userUuid);

    List<OrderDto> getUserOrders(String userUuid);

    CartDto getUserCardDetailsWithUserUUID(String userUuid);

    void deleteCardDetailsWithUserUUID(String userUuid);

    CartDto getUserCartByUserUUID(String userUuid);

    void updateUserCartWithUserUUID(String userUuid, CartDto updatedCartDto);
}
