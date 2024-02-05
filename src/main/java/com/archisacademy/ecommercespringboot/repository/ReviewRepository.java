package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.Review;
import com.archisacademy.ecommercespringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByUuid(String uuid);

    List<Review> findAllReviewsByUserUuid(String uuid);

    List<Review> findAllByUser(User user); // uuid

}