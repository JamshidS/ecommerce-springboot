package com.archisacademy.ecommercespringboot.controller;

import com.archisacademy.ecommercespringboot.dto.PaymentDto;
import com.archisacademy.ecommercespringboot.model.Payment;
import com.archisacademy.ecommercespringboot.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/save")
    public ResponseEntity<Payment> saveCustomerCartDetails(@RequestBody PaymentDto paymentDto) {
        Payment payment = paymentService.saveCustomerCartDetails(paymentDto);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userUuid}")
    public ResponseEntity<PaymentDto> getUserCartDetailsWithUserUuid(@PathVariable String userUuid) {
        PaymentDto paymentDto = paymentService.getUserCartDetailsWithUserUuid(userUuid);
        return new ResponseEntity<>(paymentDto, HttpStatus.OK);
    }

    @PutMapping("/return/{userUuid}")
    public ResponseEntity<String> returnPaymentBackToUser(@PathVariable String userUuid, @RequestBody PaymentDto paymentDto) {
        String message = paymentService.returnPaymentBackToUser(userUuid, paymentDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{paymentUuid}")
    public ResponseEntity<PaymentDto> getPaymentWithPaymentUuid(@PathVariable String paymentUuid) {
        PaymentDto paymentDto = paymentService.getPaymentWithPaymentUuid(paymentUuid);
        return new ResponseEntity<>(paymentDto, HttpStatus.OK);
    }

    @PutMapping("/update/{userUuid}")
    public ResponseEntity<Void> updatePaymentByUserUuid(@PathVariable String userUuid, @RequestBody PaymentDto updatedPaymentDto) {
        paymentService.updatePaymentByUserUuid(userUuid, updatedPaymentDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{userUuid}")
    public ResponseEntity<Void> deletePaymentByUserUuid(@PathVariable String userUuid) {
        paymentService.deletePaymentByUserUuid(userUuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
