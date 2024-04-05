package com.archisacademy.ecommercespringboot.mapper.impl;

import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.dto.ShippingDto;
import com.archisacademy.ecommercespringboot.dto.UserDto;
import com.archisacademy.ecommercespringboot.enums.UserRole;
import com.archisacademy.ecommercespringboot.mapper.ShippingMapper;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.Shipping;
import com.archisacademy.ecommercespringboot.model.User;
import com.archisacademy.ecommercespringboot.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShippingMapperImpl implements ShippingMapper {
    private final OrderRepository orderRepository;

    public ShippingMapperImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public ShippingDto toDto(Shipping shipping) {
        if (shipping == null) {
            return null;
        }
        ShippingDto shippingDto = new ShippingDto();
        shippingDto.setAddress(shipping.getAddress());
        shippingDto.setShippedAt(shipping.getShippedAt());
        shippingDto.setSenderuuid(shipping.getSenderuuid());
        shippingDto.setOrderUuid(shipping.getOrder().getUuid());

        return shippingDto;
    }

    @Override
    public Shipping toEntity(ShippingDto shippingDto) {
        if (shippingDto == null) {
            return null;
        }
        Shipping shipping = new Shipping();
        shipping.setAddress(shippingDto.getAddress());
        shipping.setShippedAt(shippingDto.getShippedAt());
        shipping.setSenderuuid(shippingDto.getSenderuuid());
        shipping.setOrder(orderRepository.findByUuid(shippingDto.getOrderUuid()).get());
        return shipping;
    }

    @Override
    public List<ShippingDto> toDtoList(List<Shipping> shippingList) {
        if (shippingList == null) {
            return null;
        }
        List<ShippingDto> shippingDtoList = new ArrayList<>();
        for (Shipping shipping : shippingList) {
            shippingDtoList.add(toDto(shipping));
        }
        return shippingDtoList;
    }
}
