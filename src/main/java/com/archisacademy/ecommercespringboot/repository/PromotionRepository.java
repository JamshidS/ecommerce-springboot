package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Optional<Promotion> findByUuid(String uuid);
}
