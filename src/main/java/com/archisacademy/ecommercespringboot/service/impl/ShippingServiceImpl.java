package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.ShippingDto;
import com.archisacademy.ecommercespringboot.model.Shipping;
import com.archisacademy.ecommercespringboot.repository.ShippingRepository;
import com.archisacademy.ecommercespringboot.service.ShippingService;

import java.util.Collections;
import java.util.Optional;

public class ShippingServiceImpl implements ShippingService {

    private final ShippingRepository shippingRepository;

    public ShippingServiceImpl(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    @Override
    public String createShipping(ShippingDto shippingDto) {
        Shipping shippingForDb = new Shipping();
        shippingForDb.setAddress(shippingDto.getAddress());
        shippingForDb.setShippedAt(shippingDto.getShippedAt());

        shippingRepository.save(shippingForDb);
        return "Shipping successfully created";
    }

    @Override
    public String updateShipping(Long id, ShippingDto shippingDto) {
        Optional<Shipping> shippingForUpdate = shippingRepository.findById(id);

        return null;
    }

    @Override
    public String deleteShipping(Long id) {
        return null;
    }

    @Override
    public ShippingDto getShippingById(Long id) {
        Optional<Shipping> shippingOptional = shippingRepository.findById(id);
        if (shippingOptional.isEmpty()){
            throw new RuntimeException("Shipping not found");
        }
        Shipping shipping = shippingOptional.get();

        return new ShippingDto(shipping.getAddress(),
                shipping.getShippedAt(),
                Collections.singletonList(shipping.getProductList()),
                Collections.singletonList(shipping.getUserList()));
    }

    @Override
    public ShippingDto getAllShippings() {
        return null;
    }
}
