package com.archisacademy.ecommercespringboot.controller;

import com.archisacademy.ecommercespringboot.dto.ShippingDto;
import com.archisacademy.ecommercespringboot.service.ShippingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shippings")
public class ShippingController {

    private final ShippingService shippingService;

    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createShipping(@RequestBody ShippingDto shippingDto) {
        return new ResponseEntity<>(shippingService.createShipping(shippingDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateShipping(@PathVariable Long id, @RequestBody ShippingDto shippingDto) {
        return new ResponseEntity<>(shippingService.updateShipping(id, shippingDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteShipping(@PathVariable Long id) {
        shippingService.deleteShipping(id);
        return new ResponseEntity<>("Shipping successfully deleted", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShippingDto> getShippingById(@PathVariable Long id) {
        return new ResponseEntity<>(shippingService.getShippingById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ShippingDto>> getAllShippings() {
        return new ResponseEntity<>(shippingService.getAllShippings(), HttpStatus.OK);
    }
}
