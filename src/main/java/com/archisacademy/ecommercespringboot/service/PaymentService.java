package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.PaymentDto;
import com.archisacademy.ecommercespringboot.model.Payment;

public interface PaymentService {
    Payment saveCustomerCartDetails(PaymentDto paymentDto);
    PaymentDto getCustomerCartWithUserUuid(String userUuid);
}
