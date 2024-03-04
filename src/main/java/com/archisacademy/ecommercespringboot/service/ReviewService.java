package com.archisacademy.ecommercespringboot.service;

import com.archisacademy.ecommercespringboot.dto.ReviewDto;
import com.archisacademy.ecommercespringboot.model.Review;

import java.util.List;

public interface ReviewService {
    ReviewDto saveReview(ReviewDto reviewDto);

    ReviewDto getReviewByUserUuid(String userUuid);

    List<ReviewDto> getReviewByProductUuid(String productUuid);

    String updateReviewByUserUuid(String userUuid, ReviewDto updatedReviewDto);

    void deleteReviewByUserUuid(String userUuid);

<<<<<<< HEAD
    void deleteReviewByProductUuid(String reviewUuid);

    List<ReviewDto> getAllReviewsByProductUUID(String productUUID);

    String approveReview(String reviewUUID);

=======
    void deleteReviewByProductUuid(String productUuid);

    String approveReview(String reviewUUID);

    List<ReviewDto> getAllReviewsByProductUUID(String productUUID);
>>>>>>> 91cbb2d753978fff1c32abe66b9c55afe0a91666
}
