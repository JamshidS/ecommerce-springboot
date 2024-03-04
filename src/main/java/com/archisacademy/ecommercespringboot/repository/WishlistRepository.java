package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Wishlist findByUuid(String uuid);

    Optional<Wishlist> findByUserUuidAndProductUuid(String userUuid, String productUuid);

    List<Wishlist> findAllByUserUuid(String userUuid);
}
