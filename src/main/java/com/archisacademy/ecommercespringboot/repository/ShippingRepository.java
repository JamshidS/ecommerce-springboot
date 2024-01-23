package com.archisacademy.ecommercespringboot.repository;

import com.archisacademy.ecommercespringboot.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {
}
