package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.dto.ShippingDto;
import com.archisacademy.ecommercespringboot.dto.UserDto;
import com.archisacademy.ecommercespringboot.enums.UserRole;
import com.archisacademy.ecommercespringboot.mapper.ShippingMapper;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.Shipping;
import com.archisacademy.ecommercespringboot.model.User;
import com.archisacademy.ecommercespringboot.repository.OrderRepository;
import com.archisacademy.ecommercespringboot.repository.ProductRepository;
import com.archisacademy.ecommercespringboot.repository.ShippingRepository;
import com.archisacademy.ecommercespringboot.repository.UserRepository;
import com.archisacademy.ecommercespringboot.service.ShippingService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ShippingServiceImpl implements ShippingService {

    private final ShippingRepository shippingRepository;
    private final ShippingMapper shippingMapper;
    private final OrderRepository orderRepository;

    public ShippingServiceImpl(ShippingRepository shippingRepository, ShippingMapper shippingMapper, OrderRepository orderRepository) {
        this.shippingRepository = shippingRepository;
        this.shippingMapper = shippingMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    public String createShipping(ShippingDto shippingDto) {
        Shipping shippingForDb = new Shipping();
        shippingForDb.setAddress(shippingDto.getAddress());
        shippingForDb.setShippedAt(shippingDto.getShippedAt());
        shippingForDb.setSenderuuid(shippingDto.getSenderuuid());
        shippingForDb.setOrder(orderRepository.findByUuid(shippingDto.getOrderUuid()).get());



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
        shipping.setSenderuuid(shippingDto.getSenderuuid());
        shipping.setOrder(orderRepository.findByUuid(shippingDto.getOrderUuid()).get());

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

        return shippingMapper.toDto(shipping);
    }

    @Override
    public List<ShippingDto> getAllShippings() {
        List<Shipping> shippingList = shippingRepository.findAll();

        return shippingMapper.toDtoList(shippingList);
    }


}
