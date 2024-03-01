package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.PaymentDto;
import com.archisacademy.ecommercespringboot.model.Payment;

public interface PaymentService {
    Payment saveCustomerCartDetails(PaymentDto paymentDto);
    void updatePaymentByUserUuid(String userUuid, PaymentDto updatedPaymentDto);
    void deletePaymentByUserUuid(String userUuid);
    PaymentDto getUserCartDetailsWithUserUuid(String userUuid);
}
