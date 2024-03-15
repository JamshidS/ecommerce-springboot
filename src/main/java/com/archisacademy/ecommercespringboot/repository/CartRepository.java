package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.Cart;
import com.archisacademy.ecommercespringboot.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUuid(String uuid);
}
