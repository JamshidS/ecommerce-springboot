package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.ShippingDto;

public interface ShippingService {
    String createShipping(ShippingDto shippingDto);
    String updateShipping(Long id, ShippingDto shippingDto);
    String deleteShipping(Long id);
    ShippingDto getShippingById(Long id);
    ShippingDto getAllShippings();

}
