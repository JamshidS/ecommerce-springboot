package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Promotion findByUuid(String uuid);
}
