package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Wishlist findByUuid(String uuid);

    Optional<Wishlist> findByUserUuidAndProductUuid(String userUuid, String productUuid);

    List<Wishlist> findAllByUserUuid(String userUuid);

    @Query("SELECT w FROM Wishlist w JOIN w.user u WHERE u.uuid = :userUuid")
    Wishlist findByUserUuid(@Param("userUuid") String userUuid);
}
