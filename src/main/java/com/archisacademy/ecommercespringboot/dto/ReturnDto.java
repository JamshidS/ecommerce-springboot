package com.archisacademy.ecommercespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnDto {
    private String address;
    private String reason;
    private Date returnDate;
    private String userUuid;
    private String productUuid;
    private Long cartId;
    private Long orderId;


}
