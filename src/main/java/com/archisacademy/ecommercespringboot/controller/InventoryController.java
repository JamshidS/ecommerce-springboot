package com.archisacademy.ecommercespringboot.controller;

import com.archisacademy.ecommercespringboot.dto.InventoryDto;
import com.archisacademy.ecommercespringboot.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createInventory(@RequestBody InventoryDto inventoryDto) {
        return new ResponseEntity<>(inventoryService.createInventory(inventoryDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateInventory(@RequestBody InventoryDto inventoryDto, @PathVariable Long id) {
        return new ResponseEntity<>(inventoryService.updateInventory(inventoryDto, id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<InventoryDto>> getAllInventories() {
        return new ResponseEntity<>(inventoryService.getAllInventories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDto> getInventoryById(@PathVariable Long id) {
        return new ResponseEntity<>(inventoryService.getInventoryById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return new ResponseEntity<>("Inventory successfully deleted", HttpStatus.OK);
    }
}
