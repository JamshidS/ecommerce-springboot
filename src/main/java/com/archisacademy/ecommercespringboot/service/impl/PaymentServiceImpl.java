package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.PaymentDto;
import com.archisacademy.ecommercespringboot.model.Payment;
import com.archisacademy.ecommercespringboot.repository.PaymentRepository;
import com.archisacademy.ecommercespringboot.service.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        Payment payment = convertToEntity(paymentDto);
        Payment savedPayment = paymentRepository.save(payment);
        return convertToDto(savedPayment);
    }

    @Override
    public PaymentDto getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
        return convertToDto(payment);
    }

    @Override
    public PaymentDto updatePayment(Long id, PaymentDto paymentDto) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));

        existingPayment.setName(paymentDto.getName());
        existingPayment.setCardNumber(paymentDto.getCardNumber());
        existingPayment.setExpirationDate(paymentDto.getExpirationDate());
        existingPayment.setCvc(paymentDto.getCvc());
        existingPayment.setAmount(paymentDto.getAmount());
        existingPayment.setProductUuid(paymentDto.getProductUuid());

        Payment updatedPayment = paymentRepository.save(existingPayment);
        return convertToDto(updatedPayment);
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    private PaymentDto convertToDto(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();
        BeanUtils.copyProperties(payment, paymentDto);
        return paymentDto;
    }

    private Payment convertToEntity(PaymentDto paymentDto) {
        Payment payment = new Payment();
        BeanUtils.copyProperties(paymentDto, payment);
        return payment;
    }
}
