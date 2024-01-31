package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    void saveUser(UserDto userDto);
    void updateUser(Long id, UserDto updatedUserDto);
    void deleteUser(Long id);
}
