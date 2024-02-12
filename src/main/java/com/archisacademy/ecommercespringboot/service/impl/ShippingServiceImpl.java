package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.ShippingDto;
import com.archisacademy.ecommercespringboot.model.Shipping;
import com.archisacademy.ecommercespringboot.repository.ShippingRepository;
import com.archisacademy.ecommercespringboot.service.ShippingService;

import java.util.Collections;
import java.util.List;
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
        if (shippingForUpdate.isEmpty()){
            throw new RuntimeException("Shipping not found");
        }
        Shipping shipping = shippingForUpdate.get();
        shipping.setAddress(shippingDto.getAddress());
        shipping.setShippedAt(shippingDto.getShippedAt());

        shippingRepository.save(shipping);

        return "Shipping successfully updated";
    }

    @Override
    public void deleteShipping(Long id) {
        getShippingById(id);

        shippingRepository.deleteById(id);
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
    public List<ShippingDto> getAllShippings() {
        List<Shipping> shippingList = shippingRepository.findAll();

        return shippingList.stream().map(shipping -> new ShippingDto(
                shipping.getAddress(),
                shipping.getShippedAt(),
                Collections.singletonList(shipping.getProductList()),
                Collections.singletonList(shipping.getUserList())
        )).toList();
    }
}
