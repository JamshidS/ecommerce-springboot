package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.ShippingDto;
import com.archisacademy.ecommercespringboot.repository.ShippingRepository;
import com.archisacademy.ecommercespringboot.service.ShippingService;

public class ShippingServiceImpl implements ShippingService {

    private final ShippingRepository shippingRepository;

    public ShippingServiceImpl(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    @Override
    public String createShipping(ShippingDto shippingDto) {
        return null;
    }

    @Override
    public String updateShipping(Long id, ShippingDto shippingDto) {
        return null;
    }

    @Override
    public String deleteShipping(Long id) {
        return null;
    }

    @Override
    public ShippingDto getShippingById(Long id) {
        return null;
    }

    @Override
    public ShippingDto getAllShippings() {
        return null;
    }
}
