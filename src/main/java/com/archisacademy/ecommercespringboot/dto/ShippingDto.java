package com.archisacademy.ecommercespringboot.dto;

import com.archisacademy.ecommercespringboot.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingDto {
    private String address;
    private Date shippedAt;
    private String senderuuid;
    private String orderUuid;
}
