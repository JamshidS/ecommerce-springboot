package com.archisacademy.ecommercespringboot.dto.response;

import lombok.Data;

@Data
public class CartResponse {
    private Double totalActualAmount;
    private Double promotionAmount;
    private Double totalAmountAfterPromotion;
}
