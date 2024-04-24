package com.archisacademy.ecommercespringboot.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDto {
    private String uuid;
    private String price;
    private String userUuid;
    private String productUuids;
}
