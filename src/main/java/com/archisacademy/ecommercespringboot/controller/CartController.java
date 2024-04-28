package com.archisacademy.ecommercespringboot.controller;

import com.archisacademy.ecommercespringboot.dto.CartDto;
import com.archisacademy.ecommercespringboot.dto.response.CartResponse;
import com.archisacademy.ecommercespringboot.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/save")
    public ResponseEntity<CartResponse> saveCart(@RequestBody CartDto cartDto) {
        return new ResponseEntity<>(cartService.saveCart(cartDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{cartUuid}")
    public ResponseEntity<CartResponse> updateCart(
            @PathVariable String cartUuid,
            @RequestBody CartDto cartDto) {
        return new ResponseEntity<>(cartService.updateCart(cartDto, cartUuid), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartUuid}")
    public ResponseEntity<String> deleteCart(@PathVariable String cartUuid) {
        cartService.deleteCart(cartUuid);
        return new ResponseEntity<>("Cart successfully deleted", HttpStatus.OK);
    }

    @GetMapping("/{cartUuid}")
    public ResponseEntity<CartDto> getCartByUuid(@PathVariable String cartUuid) {
        return new ResponseEntity<>(cartService.getCartByUuid(cartUuid), HttpStatus.OK);
    }

    @GetMapping("/user/{userUuid}")
    public ResponseEntity<CartDto> getCartByUserUuid(@PathVariable String userUuid) {
        return new ResponseEntity<>(cartService.getCartByUserUuid(userUuid), HttpStatus.OK);
    }
}
