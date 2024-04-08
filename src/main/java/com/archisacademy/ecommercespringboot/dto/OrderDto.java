package com.archisacademy.ecommercespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String uuid;
    private String orderNumber;
    private Timestamp orderDate;
    private Double totalAmount;
    private String orderStatus;
    private String userUuid;
    private List<String> productUuid;
}
