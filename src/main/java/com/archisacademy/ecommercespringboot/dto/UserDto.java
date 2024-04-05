package com.archisacademy.ecommercespringboot.dto;

import com.archisacademy.ecommercespringboot.enums.UserRole;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Getter
@Setter
public class UserDto {
    private String uuid;
    private String userName;
    private String email;
    private String password;
    private String telephone;
    private String address;
    private UserRole userRole;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
