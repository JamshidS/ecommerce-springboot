package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.PromotionDto;
import com.archisacademy.ecommercespringboot.model.*;
import com.archisacademy.ecommercespringboot.model.Promotion;
import com.archisacademy.ecommercespringboot.repository.ProductRepository;
import com.archisacademy.ecommercespringboot.repository.PromotionRepository;
import com.archisacademy.ecommercespringboot.service.PromotionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    private final ProductRepository productRepository;

    public PromotionServiceImpl(PromotionRepository promotionRepository, ProductRepository productRepository) {
        this.promotionRepository = promotionRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public String savePromotion(PromotionDto promotionDto) {
        List<Product> productList = new ArrayList<>();
        for (String productUuid : promotionDto.getProductUuid()) {
            productList.add(productRepository.findByUuid(productUuid).get());
        }
        Promotion promotion = new Promotion();
        promotion.setUuid(promotionDto.getUuid());
        promotion.setName(promotionDto.getName());
        promotion.setDescription(promotionDto.getDescription());
        promotion.setDiscount(promotionDto.getDiscount());
        getPromotionCodeAndSavePromotionToDB(promotionDto, productList, promotion);
        return "Promotion saved successfully!";
    }

    private void getPromotionCodeAndSavePromotionToDB(PromotionDto promotionDto, List<Product> productList, Promotion promotion) {
        promotion.setCode(generateCode(promotionDto.getFullName(), promotionDto.getDiscount()));
        promotion.setCratedBy(promotionDto.getFullName());
        promotion.setExpirationDate(new Timestamp(System.currentTimeMillis() + (promotionDto.getDaysToAdd() * 24 * 60 * 60 * 1000)));
        promotion.setProductList(productList);
        promotionRepository.save(promotion);
    }

    @Override
    @Transactional
    public String updatePromotion(PromotionDto promotionDto) {
        Optional<Promotion> promotionOptional = promotionRepository.findByUuid(promotionDto.getUuid());
        if (promotionOptional.isEmpty()) {
            throw new RuntimeException("An error occurred during the update!");
        }
        Promotion promotion = promotionOptional.get();
        List<Product> productList = new ArrayList<>();
        for (String productUuid : promotionDto.getProductUuid()) {
            productList.add(productRepository.findByUuid(productUuid).get());
        }
        promotion.setName(promotionDto.getName());
        promotion.setDescription(promotionDto.getDescription());
        promotion.setDiscount(promotionDto.getDiscount());
        getPromotionCodeAndSavePromotionToDB(promotionDto, productList, promotion);
        return "Promotion updated successfully!";
    }

    @Override
    @Transactional
    public void deletePromotion(String promotionUuid) {
        Optional<Promotion> promotionOptional = promotionRepository.findByUuid(promotionUuid);
        if (promotionOptional.isEmpty()) {
            throw new RuntimeException("Promotion not found!");
        }
        promotionRepository.delete(promotionOptional.get());
    }

    @Override
    public boolean isPromotionValid(String promotionUuid) {
        Optional<Promotion> promotionOptional = promotionRepository.findByUuid(promotionUuid);
        if (promotionOptional.isEmpty()) {
            throw new RuntimeException("Promotion not found!");
        }

        Promotion promotion = promotionOptional.get();

        // We can use start and end dates to check if the promotion is valid.
        Date now = new Date(); // Şu anki tarih
        Date startDate = promotion.getStartDate();
        Date endDate = promotion.getEndDate();

        // Is the current date between the start and end dates of the promotion?
        if (now.after(startDate) && now.before(endDate)) {
            return true; // Valid
        } else {
            return false; // Invalid
        }
    }

    @Override
    public List<PromotionDto> getAllPromotions() {
        List<PromotionDto> promotionDtoList = new ArrayList<>();
        List<Promotion> promotionList = promotionRepository.findAll();
        for (Promotion promotion : promotionList) {
            promotionDtoList.add(convertToDto(promotion));
        }
        return promotionDtoList;
    }

    @Override
    public PromotionDto getPromotionByUuid(String promotionUuid) {
        Optional<Promotion> promotionOptional = promotionRepository.findByUuid(promotionUuid);
        if (promotionOptional == null) {
            throw new RuntimeException("Promotion not found!");
        }
        return convertToDto(promotionOptional.get());
    }

    @Override
    public List<PromotionDto> getAllPromotionsByProductUuid(String productUuid) {
        List<PromotionDto> promotionDtoList = new ArrayList<>();
        Optional<Product> product = productRepository.findByUuid(productUuid);
        for (Promotion promotion : product.get().getPromotionList()) {
            promotionDtoList.add(convertToDto(promotion));
        }
        return promotionDtoList;
    }


    private PromotionDto convertToDto(Promotion promotion) {
        PromotionDto promotionDto = new PromotionDto();
        BeanUtils.copyProperties(promotion, promotionDto);
        return promotionDto;
    }

    private String generateCode(String fullName, double discount) {
        String[] words = fullName.split(" ");
        StringBuilder codeBuilder = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                char firstLetter = word.charAt(0);
                codeBuilder.append(firstLetter);
            }
        }
        codeBuilder.append(discount);
        return codeBuilder.toString();
    }
}
