package com.archisacademy.ecommercespringboot.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private String uuid;
    private String userName;
    private String email;
    private String password;
    private String telephone;
    private String address;
    private String userRole;
    private Date createdAt;
    private Date updatedAt;
}
