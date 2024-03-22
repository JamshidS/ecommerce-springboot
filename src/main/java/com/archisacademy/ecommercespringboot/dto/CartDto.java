package com.archisacademy.ecommercespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private String uuid;
    private LocalDate orderDate;
    private String userUuid;
    private String promotionUuid;
    private String productUuid;
}