package com.archisacademy.ecommercespringboot.dto;


import com.archisacademy.ecommercespringboot.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDto {
    private String uuid;
    private Double price;
    private Timestamp createdAt;
    private String orderNumber;
    private OrderStatus orderStatus;
    private Double refundAmount;
    private Timestamp refundDate;
    private String userUuid;
    private String productUuids;
}
