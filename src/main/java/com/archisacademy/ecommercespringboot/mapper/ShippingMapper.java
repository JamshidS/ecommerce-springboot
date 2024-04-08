package com.archisacademy.ecommercespringboot.mapper;

import com.archisacademy.ecommercespringboot.dto.ShippingDto;
import com.archisacademy.ecommercespringboot.model.Shipping;

import java.util.List;

public interface ShippingMapper {

    ShippingDto toDto(Shipping shipping);

    Shipping toEntity(ShippingDto shippingDto);

    List<ShippingDto> toDtoList(List<Shipping> shippingList);
}
