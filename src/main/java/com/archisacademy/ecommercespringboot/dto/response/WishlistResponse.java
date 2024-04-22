package com.archisacademy.ecommercespringboot.dto.response;

import com.archisacademy.ecommercespringboot.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistResponse {
    private List<ProductDto> productList;
    private String userUuid;
}
