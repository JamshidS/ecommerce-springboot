package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.PaymentDto;
import com.archisacademy.ecommercespringboot.dto.UserDto;
import com.archisacademy.ecommercespringboot.model.Payment;
import com.archisacademy.ecommercespringboot.repository.PaymentRepository;
import com.archisacademy.ecommercespringboot.service.PaymentService;
import com.archisacademy.ecommercespringboot.service.UserService;

import org.springframework.stereotype.Service;


@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserService userService;
    private final ProductService productService;


    public PaymentServiceImpl(PaymentRepository paymentRepository, UserService userService, ProductService productService) {
        this.paymentRepository = paymentRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public Payment saveCustomerCartDetails(PaymentDto paymentDto) {

        UserDto userDto = userService.getUserByUuid(paymentDto.getUserUuid());
        ProductDto productDto = productService.getProductByUuid(paymentDto.getProductUuid());

        String name = paymentDto.getName();
        String cardNumber = paymentDto.getCardNumber();
        String expirationDate = paymentDto.getExpirationDate();
        String cvc = paymentDto.getCvc();
        Double amount = paymentDto.getAmount();

        Payment payment = new Payment();
        payment.setName(name);
        payment.setCardNumber(cardNumber);
        payment.setExpirationDate(expirationDate);
        payment.setCvc(cvc);
        payment.setAmount(amount);
        //  payment.setUserUuid(userDto.getUuid());
        //  payment.setProductUuid(productDto.getUuid());

        return paymentRepository.save(payment);
    }

    @Override
    public PaymentDto getCustomerCartWithUserUuid(String userUuid) {
        Payment payment = paymentRepository.findByUserUuid(userUuid);
        if (payment == null) {
            return null;
        }

        PaymentDto paymentDto = new PaymentDto();
        //      paymentDto.setUuid(payment.getUuid());
        paymentDto.setName(payment.getName());
        paymentDto.setCardNumber(payment.getCardNumber());
        paymentDto.setExpirationDate(payment.getExpirationDate());
        paymentDto.setCvc(payment.getCvc());
        paymentDto.setAmount(payment.getAmount());
        //   paymentDto.setProductUuid(payment.getProductUuid());

        return paymentDto;
    }
}
