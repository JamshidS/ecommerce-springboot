package com.archisacademy.ecommercespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String uuid;
    private String orderNumber;
    private LocalDate orderDate;
    private Double totalAmount;
    private String orderStatus;
    private String userUuid;
}
