package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByReferenceCode(String productUuid);
}
