package com.archisacademy.ecommercespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private String name;

    private String uuid;

    private String description;

    private double price;

    private Date createdAt;

    private Date updatedAt;

    private String categoryUUID;

    private List<UserDto> userLists;

    private List<Object> promotionList;
}
