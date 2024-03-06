package com.archisacademy.ecommercespringboot.mapper;

import com.archisacademy.ecommercespringboot.dto.ProductDto;
import com.archisacademy.ecommercespringboot.dto.PromotionDto;
import com.archisacademy.ecommercespringboot.mapper.impl.ProductMapperImpl;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.Promotion;

import java.util.ArrayList;
import java.util.List;

public interface PromotionMapper {
    PromotionDto toPromotionDto(Promotion promotion);
    Promotion toPromotion(PromotionDto promotionDto);
}
