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
        return new ResponseEntity<>(paymentService.saveCustomerCartDetails(paymentDto), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userUuid}")
    public ResponseEntity<PaymentDto> getUserCartDetailsWithUserUuid(@PathVariable String userUuid) {
        return new ResponseEntity<>(paymentService.getUserCartDetailsWithUserUuid(userUuid), HttpStatus.OK);
    }

    @PutMapping("/return/{userUuid}")
    public ResponseEntity<String> returnPaymentBackToUser(@PathVariable String userUuid, @RequestBody PaymentDto paymentDto) {
        boolean paymentReturned = paymentService.returnPaymentBackToUser(userUuid, paymentDto);
        if (paymentReturned) {
            return ResponseEntity.ok("Payment returned successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient funds for return");
        }
    }

    @GetMapping("/{paymentUuid}")
    public ResponseEntity<PaymentDto> getPaymentWithPaymentUuid(@PathVariable String paymentUuid) {
        return new ResponseEntity<>(paymentService.getPaymentWithPaymentUuid(paymentUuid), HttpStatus.OK);
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
