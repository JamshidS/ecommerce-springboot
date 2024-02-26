package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.ReviewDto;
import com.archisacademy.ecommercespringboot.model.Review;

import java.util.List;

public interface ReviewService {
    Review saveReview(ReviewDto reviewDto);

    ReviewDto getReviewByUserUuid(String userUuid);

    ReviewDto getReviewByProductUuid(String productUuid);

    void updateReviewByUserUuid(String userUuid, ReviewDto updatedReviewDto);

    void updateReviewByProductUuid(String productUuid, ReviewDto updatedReviewDto);

    void deleteReviewByUserUuid(String userUuid);

    void deleteReviewByProductUuid(String productUuid);

    List<ReviewDto> getAllReviewsByProductUUID(String productUUID);
}
