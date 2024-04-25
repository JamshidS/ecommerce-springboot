package com.archisacademy.ecommercespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String name;

    private String uuid;

    private String description;

    private double price;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private String categoryUuid;

    private List<PromotionDto> promotionList;
}
