package com.archisacademy.ecommercespringboot.mapper;

import com.archisacademy.ecommercespringboot.dto.UserDto;
import com.archisacademy.ecommercespringboot.enums.UserRole;
import com.archisacademy.ecommercespringboot.model.User;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUuid(user.getUuid());
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setTelephone(user.getTelephone());
        userDto.setAddress(user.getAddress());
        userDto.setUserRole(user.getUserRole().toString());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());
        return userDto;
    }

    public static User toUser(UserDto userDto) {
        User user = new User();
        user.setUuid(userDto.getUuid());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setTelephone(userDto.getTelephone());
        user.setAddress(userDto.getAddress());
        user.setUserRole(UserRole.valueOf(userDto.getUserRole()));
        user.setCreatedAt(userDto.getCreatedAt());
        user.setUpdatedAt(userDto.getUpdatedAt());
        return user;
    }
}
