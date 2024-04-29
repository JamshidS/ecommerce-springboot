package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.PaymentDto;
import com.archisacademy.ecommercespringboot.mapper.PaymentMapper;
import com.archisacademy.ecommercespringboot.model.Payment;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.User;
import com.archisacademy.ecommercespringboot.repository.PaymentRepository;
import com.archisacademy.ecommercespringboot.repository.ProductRepository;
import com.archisacademy.ecommercespringboot.repository.UserRepository;
import com.archisacademy.ecommercespringboot.service.PaymentService;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, UserRepository userRepository, ProductRepository productRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    @Transactional
    public Payment saveCustomerCartDetails(PaymentDto paymentDto) {
        Optional<Product> product = productRepository.findByUuid(paymentDto.getProductUuid().trim());
        Optional<User> user = userRepository.findByUuid(paymentDto.getUserUuid().trim());

        if (product.isEmpty() || user.isEmpty()) {
            throw new RuntimeException("uuid not found");
        }
        Payment payment = new Payment();
        payment.setName(paymentDto.getName());
        payment.setCardNumber(paymentDto.getCardNumber());
        payment.setExpirationDate(paymentDto.getExpirationDate());
        payment.setCvc(paymentDto.getCvc());
        payment.setAmount(paymentDto.getAmount());

        return paymentRepository.save(payment);
    }

    @Override
    public PaymentDto getUserCartDetailsWithUserUuid(String userUuid) {
        Optional<Payment> paymentOptional = paymentRepository.findByUserUuid(userUuid.trim());
        if (paymentOptional.isEmpty()) {
            throw new RuntimeException("payment not found");
        }
        Payment payment = paymentOptional.get();
        return paymentMapper.convertToDto(payment);
    }

    @Override
    @Transactional
    public boolean returnPaymentBackToUser(String userUuid, PaymentDto paymentDto) {
        Optional<Payment> paymentOptional = paymentRepository.findByUserUuid(userUuid.trim());

        if (paymentOptional.isEmpty()) {
            throw new RuntimeException("Payment not found");
        }

        Payment payment = paymentOptional.get();

        double newAmount = payment.getAmount() - paymentDto.getAmount();
        if (newAmount < 0) {
            return false;
        }

        payment.setAmount(newAmount);
        paymentRepository.save(payment);

        return true;
    }

    @Override
    public PaymentDto getPaymentWithPaymentUuid(String paymentUuid) {
        Optional<Payment> paymentOptional = paymentRepository.findByUuid(paymentUuid.trim());
        if (paymentOptional.isEmpty()) {
            throw new RuntimeException("payment not found");
        }
        Payment payment = paymentOptional.get();
        return paymentMapper.convertToDto(payment);
    }

    @Override
    @Transactional
    public String updatePaymentByUserUuid(String userUuid, PaymentDto updatedPaymentDto) {
        Optional<Payment> existingPayment = paymentRepository.findByUserUuid(userUuid.trim());
        if (existingPayment.isPresent()) {
            Payment payment = existingPayment.get();
            payment.setName(updatedPaymentDto.getName());
            payment.setCardNumber(updatedPaymentDto.getCardNumber());
            payment.setExpirationDate(updatedPaymentDto.getExpirationDate());
            payment.setCvc(updatedPaymentDto.getCvc());
            paymentRepository.save(payment);
            return "Payment updated successfully";
        } else {
            return "Payment not found";
        }
    }

    @Override
    @Transactional
    public void deletePaymentByUserUuid(String userUuid) {
        Optional<Payment> existingPayment = paymentRepository.findByUserUuid(userUuid.trim());
        existingPayment.ifPresent(paymentRepository::delete);
    }
}
