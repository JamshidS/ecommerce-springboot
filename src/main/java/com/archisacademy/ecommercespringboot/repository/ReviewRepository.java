package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.Review;
import com.archisacademy.ecommercespringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByUuid(String uuid);

    List<Review> findAllByUser(User user); // uuid

    List<Review> findAllByProductUuid(String productUuid);

    Review findByUserUuidAndProductUuid(String userUuid, String productUuid);

    String deleteByUuid(String uuid);

    List<Review> findAllByUuid(String uuid);

    Review findByUserUuid(String userUuid);
}
