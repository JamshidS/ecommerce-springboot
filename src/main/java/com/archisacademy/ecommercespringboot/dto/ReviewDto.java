package com.archisacademy.ecommercespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    private String uuid;
    private short rating;
    private String comment;
    private Date createdAt;
    private String userUuid;
    private String productUuid;
<<<<<<< HEAD
    private Boolean isApproved;
=======
    private boolean isApproved;
>>>>>>> 91cbb2d753978fff1c32abe66b9c55afe0a91666
}
