package com.archisacademy.ecommercespringboot.service;


import com.archisacademy.ecommercespringboot.dto.PromotionDto;
import com.archisacademy.ecommercespringboot.model.Promotion;
import jakarta.transaction.Transactional;

import java.util.List;

public interface PromotionService {
    String savePromotion(PromotionDto promotionDto);

    String updatePromotion(PromotionDto promotionDto);

    void deletePromotion(String promotionUuid);

    boolean isPromotionValid(String promotionUuid);

    List<PromotionDto> getAllPromotions();

    PromotionDto getPromotionByUuid(String promotionUuid);

    List<PromotionDto> getAllPromotionsByProductUuid(String productUuid);

}
