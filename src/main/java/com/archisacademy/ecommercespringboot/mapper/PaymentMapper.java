package com.archisacademy.ecommercespringboot.mapper;

import com.archisacademy.ecommercespringboot.dto.PaymentDto;
import com.archisacademy.ecommercespringboot.model.Payment;

public interface PaymentMapper {
    PaymentDto convertToDto(Payment payment);
}
