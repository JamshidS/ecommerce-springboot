package com.archisacademy.ecommercespringboot.mapper.impl;

import com.archisacademy.ecommercespringboot.dto.PaymentDto;
import com.archisacademy.ecommercespringboot.mapper.PaymentMapper;
import com.archisacademy.ecommercespringboot.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapperImpl implements PaymentMapper {
    @Override
    public PaymentDto convertToDto(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setUuid(payment.getUuid());
        paymentDto.setName(payment.getName());
        paymentDto.setCardNumber(payment.getCardNumber());
        paymentDto.setExpirationDate(payment.getExpirationDate());
        paymentDto.setCvc(payment.getCvc());
        paymentDto.setAmount(payment.getAmount());
        paymentDto.setProductUuid(payment.getProduct().getUuid());
        paymentDto.setUserUuid(payment.getUser().getUuid());

        return paymentDto;
    }
}
