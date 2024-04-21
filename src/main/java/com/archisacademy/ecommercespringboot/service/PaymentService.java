package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.PaymentDto;
import com.archisacademy.ecommercespringboot.model.Payment;

public interface PaymentService {
    Payment saveCustomerCartDetails(PaymentDto paymentDto);
    String updatePaymentByUserUuid(String userUuid, PaymentDto updatedPaymentDto);
    void deletePaymentByUserUuid(String userUuid);
    PaymentDto getUserCartDetailsWithUserUuid(String userUuid);
    boolean returnPaymentBackToUser(String userUuid, PaymentDto paymentDto);
    PaymentDto getPaymentWithPaymentUuid(String paymentUuid);
}
