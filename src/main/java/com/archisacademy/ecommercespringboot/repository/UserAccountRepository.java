package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUuid(String uuid);

}
