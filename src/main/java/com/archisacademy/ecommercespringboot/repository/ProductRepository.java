package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByUuid(String uuid);

    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    List<Product> findByPrice(double price);

    List<Product> findByPromotionUuid(String promotionUuid);

    List<Product> findByUserUuid(String userUuid);

    List<Product> findByCategoryUuid(String categoryUuid);
}
