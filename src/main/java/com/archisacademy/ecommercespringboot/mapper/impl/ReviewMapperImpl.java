package com.archisacademy.ecommercespringboot.mapper.impl;

import com.archisacademy.ecommercespringboot.dto.ReviewDto;
import com.archisacademy.ecommercespringboot.mapper.ReviewMapper;
import com.archisacademy.ecommercespringboot.model.Review;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ReviewMapperImpl implements ReviewMapper {
    @Override
    public ReviewDto convertToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setUuid(review.getUuid());
        reviewDto.setRating(review.getRating());
        reviewDto.setComment(review.getComment());
        reviewDto.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        if (review.getUser() != null) {
            reviewDto.setUserUuid(review.getUser().getUuid());
        }
        if (review.getProduct() != null) {
            reviewDto.setProductUuid(review.getProduct().getUuid());
        }
        reviewDto.setIsApproved(review.getIsApproved());
        return reviewDto;
    }
}
