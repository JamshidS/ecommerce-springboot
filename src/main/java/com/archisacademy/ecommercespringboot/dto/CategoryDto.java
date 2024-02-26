package com.archisacademy.ecommercespringboot.dto;

import com.archisacademy.ecommercespringboot.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private String uuid;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private List<ProductDto> productList;
}
