package com.archisacademy.ecommercespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private String uuid;
    private String name;
    private String cardNumber;
    private String expirationDate;
    private String cvc;
    private Double amount;
    private String productUuid;
    private String userUuid;

    public String getUserUuid(){
        return this.userUuid;
    }
}


