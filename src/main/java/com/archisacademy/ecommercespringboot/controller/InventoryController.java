package com.archisacademy.ecommercespringboot.controller;

import com.archisacademy.ecommercespringboot.dto.InventoryDto;
import com.archisacademy.ecommercespringboot.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createInventory(@RequestBody InventoryDto inventoryDto) {
        String message = inventoryService.createInventory(inventoryDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/update/{inventoryId}")
    public ResponseEntity<String> updateInventory(@RequestBody InventoryDto inventoryDto, @PathVariable Long inventoryId) {
        String message = inventoryService.updateInventory(inventoryDto, inventoryId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<InventoryDto>> getAllInventories() {
        List<InventoryDto> inventories = inventoryService.getAllInventories();
        return new ResponseEntity<>(inventories, HttpStatus.OK);
    }

    @GetMapping("/{inventoryId}")
    public ResponseEntity<InventoryDto> getInventoryById(@PathVariable Long inventoryId) {
        InventoryDto inventory = inventoryService.getInventoryById(inventoryId);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{inventoryId}")
    public ResponseEntity<String> deleteInventory(@PathVariable Long inventoryId) {
        inventoryService.deleteInventory(inventoryId);
        return new ResponseEntity<>("Inventory successfully deleted", HttpStatus.OK);
    }
}
