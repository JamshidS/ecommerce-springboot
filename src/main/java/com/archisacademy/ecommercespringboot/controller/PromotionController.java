package com.archisacademy.ecommercespringboot.controller;

import com.archisacademy.ecommercespringboot.dto.PromotionDto;
import com.archisacademy.ecommercespringboot.service.PromotionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> savePromotion(@RequestBody PromotionDto promotionDto) {
        return new ResponseEntity<>(promotionService.savePromotion(promotionDto), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePromotion(@RequestBody PromotionDto promotionDto) {
        return new ResponseEntity<>(promotionService.updatePromotion(promotionDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{promotionUuid}")
    public ResponseEntity<String> deletePromotion(@PathVariable String promotionUuid) {
        promotionService.deletePromotion(promotionUuid);
        return new ResponseEntity<>("Promotion successfully deleted", HttpStatus.OK);
    }

    @GetMapping("/validate/{promotionUuid}")
    public ResponseEntity<Boolean> isPromotionValid(@PathVariable String promotionUuid) {
        return new ResponseEntity<>(promotionService.isPromotionValid(promotionUuid), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PromotionDto>> getAllPromotions() {
        return new ResponseEntity<>(promotionService.getAllPromotions(), HttpStatus.OK);
    }

    @GetMapping("/{promotionUuid}")
    public ResponseEntity<PromotionDto> getPromotionByUuid(@PathVariable String promotionUuid) {
        return new ResponseEntity<>(promotionService.getPromotionByUuid(promotionUuid), HttpStatus.OK);
    }

    @GetMapping("/product/{productUuid}")
    public ResponseEntity<List<PromotionDto>> getAllPromotionsByProductUuid(@PathVariable String productUuid) {
        return new ResponseEntity<>(promotionService.getAllPromotionsByProductUuid(productUuid), HttpStatus.OK);
    }
}
