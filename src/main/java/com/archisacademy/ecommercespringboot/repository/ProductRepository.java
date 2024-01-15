package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
