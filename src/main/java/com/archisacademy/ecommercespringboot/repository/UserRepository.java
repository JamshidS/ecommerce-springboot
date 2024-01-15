package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findById(Long id);

}
