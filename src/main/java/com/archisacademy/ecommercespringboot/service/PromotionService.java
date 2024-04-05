package com.archisacademy.ecommercespringboot.service;


import com.archisacademy.ecommercespringboot.dto.PromotionDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface PromotionService {
    String savePromotion(PromotionDto promotionDto);
    String updatePromotion(PromotionDto promotionDto);
    void deletePromotion(String promotionUuid);
    List<PromotionDto> getAllPromotions();
}
