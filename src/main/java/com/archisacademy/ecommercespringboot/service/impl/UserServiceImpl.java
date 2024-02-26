package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.UserDto;
import com.archisacademy.ecommercespringboot.enums.UserRole;
import com.archisacademy.ecommercespringboot.exceptions.UserNotFoundException;
import com.archisacademy.ecommercespringboot.model.User;
import com.archisacademy.ecommercespringboot.repository.UserRepository;
import com.archisacademy.ecommercespringboot.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
      List<User> users =userRepository.findAll();
      return users.stream()
              .map(this::convertToDto)
              .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByUuid(String uuid) {
        Optional<User> optionalUser=userRepository.findByUuid(uuid);
        return optionalUser.map(this::convertToDto).orElse(null);
    }

    @Override
    @Transactional
    public void saveUser(UserDto userDto) {
        User user=convertToEntity(userDto);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(String uuid, UserDto updatedUserDto) {

        Optional<User> optionalUser=userRepository.findByUuid(uuid);

        if (optionalUser.isEmpty()){
            throw new RuntimeException("user not found");
        }
        optionalUser.ifPresent(user -> {
            user.setUserName(updatedUserDto.getUserName());
            user.setEmail(updatedUserDto.getEmail());
            user.setPassword(updatedUserDto.getPassword());
            user.setTelephone(updatedUserDto.getTelephone());
            user.setAddress(updatedUserDto.getAddress());
            user.setUserRole(UserRole.valueOf(updatedUserDto.getUserRole().toString()));
            user.setCreatedAt(updatedUserDto.getCreatedAt());
            user.setUpdatedAt(updatedUserDto.getUpdatedAt());
            userRepository.save(user);

        });
    }

    @Override
    @Transactional
    public void deleteUser(String uuid) {
        User user = userRepository.findByUuid(uuid).orElseThrow(() -> new UserNotFoundException("User could be not delete"));
        userRepository.delete(user);
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    private User convertToEntity(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }
}
