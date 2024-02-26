package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByUuid(String uuid);
}
