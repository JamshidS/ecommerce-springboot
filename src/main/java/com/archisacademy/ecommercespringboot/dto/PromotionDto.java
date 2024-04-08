package com.archisacademy.ecommercespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionDto {
    private String uuid;
    private String name;
    private String description;
    private double discount;
    private String code;
    private List<String> productUuid;
    private String fullName;
    private Long daysToAdd ;
}
