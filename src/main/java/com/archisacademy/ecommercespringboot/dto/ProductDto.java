package com.archisacademy.ecommercespringboot.dto;

import com.archisacademy.ecommercespringboot.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
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
