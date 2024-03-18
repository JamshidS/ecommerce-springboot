package com.archisacademy.ecommercespringboot.dto.response;

import com.archisacademy.ecommercespringboot.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistResponse {
    //todo:changce to product dto
    private List<Product> products;
    private String userUuid;
}
