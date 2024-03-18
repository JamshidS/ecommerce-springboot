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

import java.sql.Timestamp;
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
        review.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        review.setUser(user.get());
        review.setProduct(product.get());
        reviewRepository.save(review);
        return convertToDto(review);
    }

    @Override
    public ReviewDto getReviewByUserUuid(String userUuid, String productUuid) {
        Review review = reviewRepository.findByUserUuidAndProductUuid(userUuid, productUuid);
        if (review == null) {
            throw new RuntimeException("Review not found for userUuid: " + userUuid + " and productUuid: " + productUuid);
        }

        return convertToDto(review);
    }


    @Override
    public List<ReviewDto> getReviewByProductUuid(String productUuid) {
        List<Review> reviews = reviewRepository.findAllByProductUuid(productUuid);
        if (reviews.isEmpty()) {
            throw new RuntimeException("No reviews found for productUuid: " + productUuid);
        }

        return reviews.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    @Override
    public String updateReviewByUserUuid(String userUuid, ReviewDto updatedReviewDto) {
        Review existingReview = reviewRepository.findByUserUuid(userUuid);
        if (existingReview != null) {
            existingReview.setRating(updatedReviewDto.getRating());
            existingReview.setComment(updatedReviewDto.getComment());
            reviewRepository.save(existingReview);
            return "Review updated successfully";
        }
        return "Review not found";
    }

    @Override
    public String deleteReviewByUserUuid(String userUuid, String productUuid) {
        Review existingReview = reviewRepository.findByUserUuidAndProductUuid(userUuid, productUuid);
        if (existingReview != null) {
            reviewRepository.delete(existingReview);
            return "Review deleted successfully";
        }
        throw new RuntimeException("Review not found for userUuid: " + userUuid);
    }

    @Override
    public String deleteReviewsByReviewUuid(String reviewUuid) {
        List<Review> existingReviews = reviewRepository.findAllByUuid(reviewUuid);
        if (!existingReviews.isEmpty()) {
            reviewRepository.deleteAll(existingReviews);
            return "Reviews deleted successfully";
        }
        throw new RuntimeException("No reviews found for reviewUuid: " + reviewUuid);
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
                response.add(convertToDto(review));
            }
        }
        return response;
    }

    @Override
    public String approveReview(String reviewUUID) {
        Optional<Review> optionalReview = reviewRepository.findByUuid(reviewUUID);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setIsApproved(true);
            reviewRepository.save(review);
            return "Review has been approved successfully";
        }
        throw new RuntimeException("Review not found");
    }

    public ReviewDto convertToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setUuid(review.getUuid());
        reviewDto.setRating(review.getRating());
        reviewDto.setComment(review.getComment());
        reviewDto.setCreatedAt(review.getCreatedAt());
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