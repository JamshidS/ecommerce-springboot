package com.archisacademy.ecommercespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private String uuid;
    private short rating;
    private String comment;
    private Timestamp createdAt;
    private String userUuid;
    private String productUuid;
    private Boolean isApproved;
}
