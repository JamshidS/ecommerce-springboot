package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.PaymentDto;
import com.archisacademy.ecommercespringboot.dto.ReturnDto;
import com.archisacademy.ecommercespringboot.model.*;
import com.archisacademy.ecommercespringboot.repository.*;
import com.archisacademy.ecommercespringboot.service.PaymentService;
import com.archisacademy.ecommercespringboot.service.ReturnService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReturnServiceImpl implements ReturnService {

    private final ReturnRepository returnRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final PaymentService paymentService;

    public ReturnServiceImpl(ReturnRepository returnRepository, UserRepository userRepository, CartRepository cartRepository, OrderRepository orderRepository, ProductRepository productRepository, PaymentService paymentService) {
        this.returnRepository = returnRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.paymentService = paymentService;
    }

    @Override
    public String createReturn(ReturnDto returnDto, String paymentUuid) {
        PaymentDto paymentDto = paymentService.getPaymentWithPaymentUuid(paymentUuid); // Get the payment details


        String returnPaymentMessage = paymentService.returnPaymentBackToUser(returnDto.getUserUuid(), paymentDto); // Return the payment to the user
        if (returnPaymentMessage.equals("Payment return successful")) { // todo: this kind of messages should be in english not Turkish or other language

            Product product = productRepository.findByUuid(returnDto.getProductUuid()).orElseThrow(() -> new RuntimeException("Product not found"));

            Return returnObj = new Return();
            returnObj.setAddress(returnDto.getAddress());
            returnObj.setReason(returnDto.getReason());
            returnObj.setReturnDate(returnDto.getReturnDate());
            returnObj.setUser(userRepository.findByUuid(returnDto.getUserUuid()).orElseThrow(() -> new RuntimeException("User not found")));
            returnObj.setProduct(product);
            returnObj.setCart(cartRepository.findById(returnDto.getCartId()).orElseThrow(() -> new RuntimeException("Cart not found")));
            returnObj.setOrder(orderRepository.findById(returnDto.getOrderId()).orElseThrow(() -> new RuntimeException("Order not found")));

            product.getInventory().setQuantity(product.getInventory().getQuantity() + 1); // Increase the quantity of the product in the inventory by 1

            returnRepository.save(returnObj);

            return "Return successfully created";
        } else {
            throw new RuntimeException("Geri ödeme işleminde hata oluştu: " + returnPaymentMessage);
        }
    }

    @Override
    public String updateReturn(ReturnDto returnDto) {
        Return returnForUpdate = new Return();
        returnForUpdate.setAddress(returnDto.getAddress());
        returnForUpdate.setReason(returnDto.getReason());
        returnForUpdate.setReturnDate(returnDto.getReturnDate());
        returnForUpdate.setUser(userRepository.findByUuid(returnDto.getUserUuid()).orElseThrow(() -> new RuntimeException("User not found")));
        returnForUpdate.setProduct(productRepository.findByUuid(returnDto.getProductUuid()).orElseThrow(() -> new RuntimeException("Product not found")));
        returnForUpdate.setCart(cartRepository.findById(returnDto.getCartId()).orElseThrow(() -> new RuntimeException("Cart not found")));
        returnForUpdate.setOrder(orderRepository.findById(returnDto.getOrderId()).orElseThrow(() -> new RuntimeException("Order not found")));

        returnRepository.save(returnForUpdate);

        return "Return successfully updated";
    }

    @Override
    public List<ReturnDto> getAllReturns() {
        List<Return> returnList = returnRepository.findAll();

        return returnList.stream().map(returnObj -> new ReturnDto(
                returnObj.getAddress(),
                returnObj.getReason(),
                returnObj.getReturnDate(),
                returnObj.getUser().getUuid(),
                returnObj.getProduct().getUuid(),
                returnObj.getCart().getId(),
                returnObj.getOrder().getId()
        )).toList();
    }

    @Override
    public ReturnDto getReturnById(Long returnId) {
        Optional<Return> returnOptional = returnRepository.findById(returnId);
        if (returnOptional.isEmpty()) {
            throw new RuntimeException("Return not found");
        }
        Return returnObj = returnOptional.get();
        return new ReturnDto(
                returnObj.getAddress(),
                returnObj.getReason(),
                returnObj.getReturnDate(),
                returnObj.getUser().getUuid(),
                returnObj.getProduct().getUuid(),
                returnObj.getCart().getId(),
                returnObj.getOrder().getId()
        );
    }

    @Override
    public void deleteReturn(Long returnId) {
        Optional<Return> returnOptional = returnRepository.findById(returnId);
        if (returnOptional.isEmpty()) {
            throw new RuntimeException("Return not found");
        }
        returnRepository.delete(returnOptional.get());

    }
}
