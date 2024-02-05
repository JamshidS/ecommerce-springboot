package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUuıd(String uuid);
    void deleteByUUID(String uuid);
}
