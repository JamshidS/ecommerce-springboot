package com.archisacademy.ecommercespringboot.mapper;

import com.archisacademy.ecommercespringboot.dto.UserDto;
import com.archisacademy.ecommercespringboot.enums.UserRole;
import com.archisacademy.ecommercespringboot.model.User;

public interface UserMapper {

    UserDto toUserDto(User user);
    User toUser(UserDto userDto);
}
