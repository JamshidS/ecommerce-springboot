package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.Return;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ReturnRepository extends JpaRepository<Return,Long> {

}
