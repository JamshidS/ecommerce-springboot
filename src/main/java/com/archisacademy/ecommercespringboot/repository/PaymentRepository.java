package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByUuid(String uuid);

    Optional<Payment> findByUserUuid(String userUuid);

    Optional<Payment> findByProductUuid(String productUuid);

}
