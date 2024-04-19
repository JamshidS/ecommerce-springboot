package com.archisacademy.ecommercespringboot.controller;


import com.archisacademy.ecommercespringboot.dto.CartDto;
import com.archisacademy.ecommercespringboot.dto.OrderDto;
import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.dto.UserDto;
import com.archisacademy.ecommercespringboot.dto.response.WishlistResponse;
import com.archisacademy.ecommercespringboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserDto> getUserByUuid(@PathVariable String uuid) {
        UserDto userDto = userService.getUserByUuid(uuid);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Void> updateUser(@PathVariable String uuid, @RequestBody UserDto updatedUserDto) {
        userService.updateUser(uuid, updatedUserDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteUser(@PathVariable String uuid) {
        userService.deleteUser(uuid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{uuid}/wishlist")
    public ResponseEntity<WishlistResponse> getWishlistByUserUUID(@PathVariable String uuid) {
        WishlistResponse wishlistResponse = userService.getWishlistByUserUUID(uuid);
        return ResponseEntity.ok(wishlistResponse);
    }

    @PostMapping("/{uuid}/wishlist")
    public ResponseEntity<Void> updateWishlistForUser(@PathVariable String uuid, @RequestBody List<String> productUuids) {
        userService.updateWishlistForUser(uuid, productUuids);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{uuid}/products")
    public ResponseEntity<List<ProductDto>> getAllProductsByUserUuid(@PathVariable String uuid) {
        List<ProductDto> products = userService.getAllProductsByUserUuid(uuid);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{uuid}/orders")
    public ResponseEntity<List<OrderDto>> getUserOrders(@PathVariable String uuid) {
        List<OrderDto> orders = userService.getUserOrders(uuid);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{uuid}/cart/details")
    public ResponseEntity<CartDto> getUserCardDetailsWithUserUUID(@PathVariable String uuid) {
        CartDto cartDto = userService.getUserCardDetailsWithUserUUID(uuid);
        return ResponseEntity.ok(cartDto);
    }

    @GetMapping("/{uuid}/cart")
    public ResponseEntity<CartDto> getUserCartByUserUUID(@PathVariable String uuid) {
        CartDto cartDto = userService.getUserCartByUserUUID(uuid);
        return ResponseEntity.ok(cartDto);
    }

    @DeleteMapping("/{uuid}/cart")
    public ResponseEntity<Void> deleteCardDetailsWithUserUUID(@PathVariable String uuid) {
        userService.deleteCardDetailsWithUserUUID(uuid);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{uuid}/cart")
    public ResponseEntity<Void> updateUserCartWithUserUUID(@PathVariable String uuid, @RequestBody CartDto updatedCartDto) {
        userService.updateUserCartWithUserUUID(uuid, updatedCartDto);
        return ResponseEntity.ok().build();
    }
}
