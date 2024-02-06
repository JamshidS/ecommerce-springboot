package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.PaymentDto;

public interface PaymentService {
    PaymentDto createPayment(PaymentDto paymentDto);
    PaymentDto getPaymentById(Long id);
    PaymentDto updatePayment(Long id, PaymentDto paymentDto);
    void deletePayment(Long id);
}
