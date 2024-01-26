package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByUuid(String uuid);
}
