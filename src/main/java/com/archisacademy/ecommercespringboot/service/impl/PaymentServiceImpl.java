package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.PaymentDto;
import com.archisacademy.ecommercespringboot.model.Payment;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.User;
import com.archisacademy.ecommercespringboot.repository.PaymentRepository;
import com.archisacademy.ecommercespringboot.repository.ProductRepository;
import com.archisacademy.ecommercespringboot.repository.UserRepository;
import com.archisacademy.ecommercespringboot.service.PaymentService;

import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Payment saveCustomerCartDetails(PaymentDto paymentDto) {
        Optional<Product> product = Optional.ofNullable(productRepository.findByUuid(paymentDto.getProductUuid().trim()));
        Optional<User> user = userRepository.findByUuid(paymentDto.getUserUuid().trim());

        if (!product.isPresent() || !user.isPresent()) {
           throw new RuntimeException("uuid not found");
        }
        Payment payment = new Payment();
        payment.setName(paymentDto.getName());
        payment.setCardNumber(paymentDto.getCardNumber());
        payment.setExpirationDate(paymentDto.getExpirationDate());
        payment.setCvc(paymentDto.getCvc());
        payment.setAmount(paymentDto.getAmount());
        payment.setUser(user.get());
        payment.setProduct(product.get());

        return paymentRepository.save(payment);
    }

    @Override
    public PaymentDto getUserCartDetailsWithUserUuid(String userUuid) {
        Payment payment = paymentRepository.findByUserUuid(userUuid.trim());
        if (payment == null) {
            throw new RuntimeException("payment nout found");
        }

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

    @Override
    public void updatePaymentByUserUuid(String userUuid, PaymentDto updatedPaymentDto) {
        Payment existingPayment = paymentRepository.findByUserUuid(userUuid.trim());
        if (existingPayment != null) {
            existingPayment.setName(updatedPaymentDto.getName());
            existingPayment.setCardNumber(updatedPaymentDto.getCardNumber());
            existingPayment.setExpirationDate(updatedPaymentDto.getExpirationDate());
            existingPayment.setCvc(updatedPaymentDto.getCvc());
            paymentRepository.save(existingPayment);
        }
    }

    @Override
    public void deletePaymentByUserUuid(String userUuid) {
        Payment existingPayment = paymentRepository.findByUserUuid(userUuid.trim());
        if (existingPayment != null) {
            paymentRepository.delete(existingPayment);
        }
    }
}
