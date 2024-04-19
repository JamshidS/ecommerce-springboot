package com.archisacademy.ecommercespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private String uuid;
    private LocalDate orderDate;
    private String userUuid;
    private String promotionCode;
    private String[] productUuids;
    private List<ProductDto> productDtoList;
}