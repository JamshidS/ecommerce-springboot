package com.archisacademy.ecommercespringboot.controller;

import com.archisacademy.ecommercespringboot.dto.ReturnDto;
import com.archisacademy.ecommercespringboot.service.ReturnService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/returns")
public class ReturnController {

    private final ReturnService returnService;

    public ReturnController(ReturnService returnService) {
        this.returnService = returnService;
    }

    @PostMapping("/create/{paymentUuid}")
    public ResponseEntity<String> createReturn(@RequestBody ReturnDto returnDto, @PathVariable String paymentUuid) {
        return new ResponseEntity<>(returnService.createReturn(returnDto, paymentUuid), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateReturn(@RequestBody ReturnDto returnDto) {
        return ResponseEntity.ok(returnService.updateReturn(returnDto));
    }

    @GetMapping("/{returnId}")
    public ResponseEntity<ReturnDto> getReturnById(@PathVariable Long returnId) {
        return ResponseEntity.ok(returnService.getReturnById(returnId));
    }

    @GetMapping
    public ResponseEntity<List<ReturnDto>> getAllReturns() {
        return ResponseEntity.ok(returnService.getAllReturns());
    }

    @DeleteMapping("/delete/{returnId}")
    public ResponseEntity<Void> deleteReturn(@PathVariable Long returnId) {
        returnService.deleteReturn(returnId);
        return ResponseEntity.ok().build();
    }
}
