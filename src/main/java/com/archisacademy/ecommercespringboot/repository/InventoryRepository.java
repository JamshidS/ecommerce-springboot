package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long > {

}
