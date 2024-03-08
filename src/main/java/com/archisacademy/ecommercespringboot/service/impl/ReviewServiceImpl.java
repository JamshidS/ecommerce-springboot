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

        if (user.isEmpty() || product.isEmpty()) {
            throw new RuntimeException("User or product not found");
        }

        Review review = new Review();
        review.setUuid(UUID.randomUUID().toString());
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setCreatedAt(new Date());
        review.setUser(user.get());
        review.setProduct(product.get());

        reviewRepository.save(review);
        return reviewDto;
    }

    @Override
    public ReviewDto getReviewByUserUuid(String userUuid) {
        Optional<User> user = userRepository.findByUuid(userUuid);
        if(user.isEmpty()){
            throw new RuntimeException("User not found with this uuid: "+userUuid);
        }
        List<Review> review = reviewRepository.findAllByUser(user.get());
        if (review == null) {
            throw new RuntimeException("Review not found for userUuid: " + userUuid);
        }
        // map all the review to the review dto and then return it.

        return null;
    }

    @Override
    public String updateReviewByUserUuidAndProductUuid(String userUuid, ReviewDto updatedReviewDto) {
        Review existingReview = reviewRepository.findAllByUserUuidAndProductUuid(userUuid,updatedReviewDto.getProductUuid());
        if (existingReview != null) {
            existingReview.setRating(updatedReviewDto.getRating());
            existingReview.setComment(updatedReviewDto.getComment());
            reviewRepository.save(existingReview);
        }
        return "Review updated successfully";
    }

    @Override
    public void deleteReviewByUserUuid(String userUuid,String productUuid) {
        Review existingReview = reviewRepository.findAllByUserUuidAndProductUuid(userUuid,productUuid);
        if (existingReview == null) {
            throw new RuntimeException("Review not found for userUuid: " + userUuid);
        }

        reviewRepository.delete(existingReview);
    }

    @Override
    public void deleteReviewByProductUuid(String productUuid) {
        List<Review> existingReviews = reviewRepository.findAllByProductUuid(productUuid);
        if (existingReviews.isEmpty()) {
            throw new RuntimeException("No reviews found for productUuid: " + productUuid);
        }
        reviewRepository.deleteAll(existingReviews);
    }

    @Override
    public List<ReviewDto> getAllReviewsByProductUUID(String productUUID) {
        List<Review> reviews = reviewRepository.findAllReviewsByUserUuid(productUUID);
        if(reviews.isEmpty()){
            throw new RuntimeException("There is no reviews present for this product");
        }
        List<ReviewDto> response = new ArrayList<>();
        reviews.forEach(review -> {
            if (review.getIsApproved()){
                ReviewDto reviewDto = ReviewDto.builder()
                        .uuid(review.getUuid())
                        .build();
            }
        });
        return response;
    }

    @Override
    public String approveReview(String reviewUUID) {
        Optional<Review> review =reviewRepository.findByUuid(reviewUUID);
        if (review.isEmpty()){
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