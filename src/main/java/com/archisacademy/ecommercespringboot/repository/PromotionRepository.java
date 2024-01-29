package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Promotion findByUuid(String uuid);
}
