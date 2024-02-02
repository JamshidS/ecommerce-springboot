package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Wishlist findByUuid(String uuid);
}
