package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.ReviewDto;
import com.archisacademy.ecommercespringboot.mapper.ReviewMapper;
import com.archisacademy.ecommercespringboot.model.Product;
import com.archisacademy.ecommercespringboot.model.Review;
import com.archisacademy.ecommercespringboot.model.User;
import com.archisacademy.ecommercespringboot.repository.ProductRepository;
import com.archisacademy.ecommercespringboot.repository.ReviewRepository;
import com.archisacademy.ecommercespringboot.repository.UserRepository;
import com.archisacademy.ecommercespringboot.service.ReviewService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductRepository productRepository, UserRepository userRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    @Transactional
    public ReviewDto saveReview(ReviewDto reviewDto) {
        Optional<Product> product = productRepository.findByUuid(reviewDto.getProductUuid());
        Optional<User> user = userRepository.findByUuid(reviewDto.getUserUuid());

        if (user.isEmpty() || product.isEmpty()) {
            throw new RuntimeException("User or product not found");
        }

        Review review = new Review();
        review.setUuid(reviewDto.getUuid());
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        review.setUser(user.get());
        review.setProduct(product.get());
        review.setIsApproved(false);
        reviewRepository.save(review);
        return reviewMapper.convertToDto(review);
    }

    @Override
    public ReviewDto getReviewByUserUuid(String userUuid, String productUuid) {
        Review review = reviewRepository.findByUserUuidAndProductUuid(userUuid, productUuid);
        if (review == null) {
            throw new RuntimeException("Review not found for userUuid: " + userUuid + " and productUuid: " + productUuid);
        }

        return reviewMapper.convertToDto(review);
    }


    @Override
    public List<ReviewDto> getReviewByProductUuid(String productUuid) {
        List<Review> reviews = reviewRepository.findAllByProductUuid(productUuid);
        if (reviews.isEmpty()) {
            throw new RuntimeException("No reviews found for productUuid: " + productUuid);
        }

        return reviews.stream()
                .map(reviewMapper::convertToDto)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public String updateReviewByUserUuid(String userUuid, String productUuid, ReviewDto updatedReviewDto) {
        Review existingReview = reviewRepository.findByUserUuidAndProductUuid(userUuid, productUuid);
        if (existingReview != null) {
            existingReview.setRating(updatedReviewDto.getRating());
            existingReview.setComment(updatedReviewDto.getComment());
            reviewRepository.save(existingReview);
            return "Review updated successfully";
        }
        return "Review not found";
    }

    @Override
    @Transactional
    public String deleteReviewByUserUuid(String userUuid, String productUuid) {
        Review existingReview = reviewRepository.findByUserUuidAndProductUuid(userUuid, productUuid);
        if (existingReview != null) {
            reviewRepository.delete(existingReview);
            return "Review deleted successfully";
        }
        throw new RuntimeException("Review not found for userUuid: " + userUuid);
    }

    @Override
    @Transactional
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
            return new ArrayList<>();
        }
        List<ReviewDto> response = new ArrayList<>();
        for (Review review : reviews) {
            if (review.getIsApproved()) {
                response.add(reviewMapper.convertToDto(review));
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
}