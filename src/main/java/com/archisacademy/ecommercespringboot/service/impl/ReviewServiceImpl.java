package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.ReviewDto;
import com.archisacademy.ecommercespringboot.model.Review;
import com.archisacademy.ecommercespringboot.repository.ReviewRepository;
import com.archisacademy.ecommercespringboot.service.ReviewService;
import com.archisacademy.ecommercespringboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final ProductService productService;
    public ReviewServiceImpl(ReviewRepository reviewRepository, UserService userService, ProductService productService) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.productService = productService;
    }


    @Override
    public Review saveReview(ReviewDto reviewDto) {
        return null;
    }

    @Override
    public List<ReviewDto> getAllReviewsForProduct(String productUuid) {
        return null;
    }

    @Override
    public List<ReviewDto> getAllReviewsByUser(String userUuid) {
        return null;
    }

    @Override
    public ReviewDto getReviewByUuid(String reviewUuid) {
        return null;
    }

    @Override
    public ReviewDto getReviewByUserAndProduct(String userUuid, String productUuid) {
        return null;
    }

    @Override
    public Review updateReview(String reviewUuid, ReviewDto updatedReviewDto) {
        return null;
    }

    @Override
    public void deleteReview(String reviewUuid) {

    }
}
