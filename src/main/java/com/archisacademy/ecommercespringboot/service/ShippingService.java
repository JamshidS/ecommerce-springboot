package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.ShippingDto;

import java.util.List;

public interface ShippingService {
    String createShipping(ShippingDto shippingDto);
    String updateShipping(Long id, ShippingDto shippingDto);
    void deleteShipping(Long id);
    ShippingDto getShippingById(Long id);
    List<ShippingDto> getAllShippings();

}
