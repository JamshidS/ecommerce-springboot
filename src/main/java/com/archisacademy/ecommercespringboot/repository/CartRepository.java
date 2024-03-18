package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.Cart;
import com.archisacademy.ecommercespringboot.model.Payment;
import com.archisacademy.ecommercespringboot.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUuid(String cartUuid);
    Optional<Cart> findByUserUuid(String userUuid);
}
