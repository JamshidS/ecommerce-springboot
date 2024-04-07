package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.InventoryDto;
import com.archisacademy.ecommercespringboot.model.Inventory;
import com.archisacademy.ecommercespringboot.repository.InventoryRepository;
import com.archisacademy.ecommercespringboot.service.InventoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    @Transactional
    public String createInventory(InventoryDto inventoryDto) {
        Inventory inventoryForDb = new Inventory();
        inventoryForDb.setQuantity(inventoryDto.getQuantity());
        inventoryForDb.setReferenceCode(inventoryDto.getReferenceCode());

        inventoryRepository.save(inventoryForDb);
        return "Inventory successfully created";
    }

    @Override
    @Transactional
    public String updateInventory(InventoryDto inventoryDto, Long inventoryId) {
        Inventory inventoryForUpdate = inventoryRepository.getById(inventoryId);

        inventoryForUpdate.setQuantity(inventoryDto.getQuantity());
        inventoryForUpdate.setReferenceCode(inventoryDto.getReferenceCode());

        inventoryRepository.save(inventoryForUpdate);

        return "Inventory successfully updated";
    }

    @Override
    public List<InventoryDto> getAllInventories() {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        return inventoryList.stream().map(inventory -> new InventoryDto(
                inventory.getQuantity(),
                inventory.getReferenceCode()
        )).toList();
    }

    @Override
    public InventoryDto getInventoryById(Long inventoryId) {
        Optional<Inventory> inventoryOptional = inventoryRepository.findById(inventoryId);
        if (inventoryOptional.isEmpty()) {
            throw new RuntimeException("Inventory not found");
        }
        Inventory inventory = inventoryOptional.get();

        return new InventoryDto(inventory.getQuantity(), inventory.getReferenceCode());
    }

    @Override
    @Transactional
    public void deleteInventory(Long inventoryId) {
        getInventoryById(inventoryId);

        inventoryRepository.deleteById(inventoryId);

    }
}
