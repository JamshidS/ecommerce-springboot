package com.archisacademy.ecommercespringboot.dto.response;

import lombok.Data;

@Data
public class CartResponse {
    double totalActualAmount;
    double promotionAmount;
    double totalAmountAfterPromotion;
}
