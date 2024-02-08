package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.ReviewDto;
import com.archisacademy.ecommercespringboot.model.Review;

import java.util.List;

public interface ReviewService {
    Review saveReview(ReviewDto reviewDto);
    List<ReviewDto> getAllReviewsForProduct(String productUuid);
    List<ReviewDto> getAllReviewsByUser(String userUuid);
    ReviewDto getReviewByUuid(String reviewUuid);
    ReviewDto getReviewByUserAndProduct(String userUuid, String productUuid);
    Review updateReview(String reviewUuid, ReviewDto updatedReviewDto);
    void deleteReview(String reviewUuid);
}
