package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByUUID(String uuid);
}

