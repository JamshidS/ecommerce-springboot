package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUuid(String uuid);
    List<Order> findByUserUuid(String userUuid);
}
