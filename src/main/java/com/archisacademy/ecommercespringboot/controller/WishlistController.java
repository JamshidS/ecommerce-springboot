package com.archisacademy.ecommercespringboot.controller;

import com.archisacademy.ecommercespringboot.dto.WishlistDto;
import com.archisacademy.ecommercespringboot.dto.response.WishlistResponse;
import com.archisacademy.ecommercespringboot.service.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("wishlist")
public class WishlistController {
    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToWishlist(@RequestBody WishlistDto wishlistDto) {
        String message = wishlistService.addToWishlist(wishlistDto.getUserUuid(), wishlistDto.getProductUuid());
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFromWishlist(@RequestBody WishlistDto wishlistDto) {
        String message = wishlistService.removeFromWishlist(wishlistDto.getUserUuid(), wishlistDto.getProductUuid());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/user/{userUuid}")
    public ResponseEntity<WishlistResponse> getWishlistByUserUuid(@PathVariable String userUuid) {
        WishlistResponse wishlistResponse = wishlistService.getWishlistByUserUuid(userUuid);
        return new ResponseEntity<>(wishlistResponse, HttpStatus.OK);
    }

    @GetMapping("/{wishlistUuid}")
    public ResponseEntity<WishlistResponse> getWishlistByUuid(@PathVariable String wishlistUuid) {
        WishlistResponse wishlistResponse = wishlistService.getWishlistByUuid(wishlistUuid);
        return new ResponseEntity<>(wishlistResponse, HttpStatus.OK);
    }
}
