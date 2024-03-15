package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.ReviewDto;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.Review;
import com.archisacademy.ecommercespringboot.model.User;
import com.archisacademy.ecommercespringboot.repository.ProductRepository;
import com.archisacademy.ecommercespringboot.repository.ReviewRepository;
import com.archisacademy.ecommercespringboot.repository.UserRepository;
import com.archisacademy.ecommercespringboot.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ReviewDto saveReview(ReviewDto reviewDto) {
        Optional<Product> product = productRepository.findByUuid(reviewDto.getProductUuid());
        Optional<User> user = userRepository.findByUuid(reviewDto.getUserUuid());

        if (!user.isPresent() || !product.isPresent()) {
            throw new RuntimeException("User or product not found");
        }

        Review review = new Review();
        review.setUuid(reviewDto.getUuid());
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setCreatedAt(new Date());
        review.setUser(user.get());
        review.setProduct(product.get());

        reviewRepository.save(review);
        return reviewDto;
    }

    @Override
    public ReviewDto getReviewByUserUuid(String userUuid, String productUuid) {
        Review review = reviewRepository.findByUserUuid(userUuid);
        if (review == null || !review.getProduct().getUuid().equals(productUuid)) {
            throw new RuntimeException("Review not found for userUuid: " + userUuid + " and productUuid: " + productUuid);
        }

        return convertReviewToDto(review);
    }


    @Override
    public List<ReviewDto> getReviewByProductUuid(String productUuid) {
        List<Review> reviews = reviewRepository.findAllByProductUuid(productUuid);
        if (reviews.isEmpty()) {
            throw new RuntimeException("No reviews found for productUuid: " + productUuid);
        }

        return reviews.stream()
                .map(this::convertReviewToDto)
                .collect(Collectors.toList());
    }


    @Override
    public String updateReviewByUserUuid(String userUuid, ReviewDto updatedReviewDto) {
        Review existingReview = reviewRepository.findByUserUuid(userUuid);
        if (existingReview != null) {
            existingReview.setRating(updatedReviewDto.getRating());
            existingReview.setComment(updatedReviewDto.getComment());
            reviewRepository.save(existingReview);
        }
        return "Review updated successfully";
    }

    @Override
    public void deleteReviewByUserUuid(String userUuid, String productUuid) {
        Review existingReview = reviewRepository.findByUserUuidAndProductUuid(userUuid, productUuid);
        if (existingReview == null) {
            throw new RuntimeException("Review not found for userUuid: " + userUuid);
        }

        reviewRepository.delete(existingReview);
    }

    @Override
    public void deleteReviewByProductUuid(String reviewUuid) {
        List<Review> existingReviews = reviewRepository.findAllByUuid(reviewUuid);
        if (existingReviews.isEmpty()) {
            throw new RuntimeException("No reviews found for productUuid: " + reviewUuid);
        }
        reviewRepository.deleteAll(existingReviews);
    }

    @Override
    public List<ReviewDto> getAllReviewsByProductUUID(String productUUID) {
        List<Review> reviews = reviewRepository.findAllByProductUuid(productUUID);
        if (reviews.isEmpty()) {
            throw new RuntimeException("There are no reviews present for this product");
        }
        List<ReviewDto> response = new ArrayList<>();
        for (Review review : reviews) {
            if (review.getIsApproved()) {
                response.add(convertReviewToDto(review));
            }
        }
        return response;
    }


    @Override
    public String approveReview(String reviewUUID) {
        Optional<Review> review = reviewRepository.findByUuid(reviewUUID);
        if (review.isEmpty()) {
            throw new RuntimeException("Review not found");
        }
        review.get().setIsApproved(true);
        reviewRepository.save(review.get());
        return "Review has been approved successfully";
    }

    private ReviewDto convertReviewToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setUuid(review.getUuid());
        reviewDto.setRating(review.getRating());
        reviewDto.setComment(review.getComment());
        reviewDto.setCreatedAt(review.getCreatedAt());
        reviewDto.setUserUuid(review.getUser().getUuid());
        reviewDto.setProductUuid(review.getProduct().getUuid());

        return reviewDto;
    }
}