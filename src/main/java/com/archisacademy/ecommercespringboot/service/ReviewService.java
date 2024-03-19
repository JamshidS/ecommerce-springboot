package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.ReviewDto;
import com.archisacademy.ecommercespringboot.model.Review;

import java.util.List;

public interface ReviewService {
    ReviewDto saveReview(ReviewDto reviewDto);

    ReviewDto getReviewByUserUuid(String userUuid, String productUuid);

    List<ReviewDto> getReviewByProductUuid(String productUuid);

    String updateReviewByUserUuid(String userUuid, String productUuid, ReviewDto updatedReviewDto);

    String deleteReviewByUserUuid(String userUuid, String productUuid);

    String deleteReviewsByReviewUuid(String reviewUuid);

    String approveReview(String reviewUUID);

    List<ReviewDto> getAllReviewsByProductUUID(String productUUID);
}
