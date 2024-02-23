package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserByUuid(String uuid);
    void saveUser(UserDto userDto);
    void updateUser(String uuid, UserDto updatedUserDto);
    void deleteUser(String uuid);
    List<ProductDto> getUserProducts(String userUUID);
}
