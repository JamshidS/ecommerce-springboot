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

import java.util.Date;
import java.util.Optional;

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
    public Review saveReview(ReviewDto reviewDto) {
        Optional<Product> product = productRepository.findByUuid(reviewDto.getProductUuid());
        Optional<User> user = userRepository.findByUuid(reviewDto.getUserUuid());

        if (!user.isPresent() || !product.isPresent()) {
            throw new RuntimeException("uuid not found");
        }

        Review review = new Review();
        review.setUuid(reviewDto.getUuid());
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setCreatedAt(new Date());
        review.setUser(user.get());
        review.setProduct(product.get());

        return reviewRepository.save(review);
    }

    @Override
    public ReviewDto getReviewByUserUuid(String userUuid) {
        Review review = reviewRepository.findByUserUuid(userUuid);
        if (review == null) {
            throw new RuntimeException("userUuid not found");
        }

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setUuid(review.getUuid());
        reviewDto.setRating(review.getRating());
        reviewDto.setComment(review.getComment());
        reviewDto.setCreatedAt(review.getCreatedAt());
        reviewDto.setUserUuid(review.getUser().getUuid());
        reviewDto.setProductUuid(review.getProduct().getUuid());

        return reviewDto;
    }

    @Override
    public ReviewDto getReviewByProductUuid(String productUuid) {
        Review review = reviewRepository.findByProductUuid(productUuid);
        if (review == null) {
            throw new RuntimeException("productUuid not found");
        }

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setUuid(review.getUuid());
        reviewDto.setRating(review.getRating());
        reviewDto.setComment(review.getComment());
        reviewDto.setCreatedAt(review.getCreatedAt());
        reviewDto.setUserUuid(review.getUser().getUuid());
        reviewDto.setProductUuid(review.getProduct().getUuid());

        return reviewDto;
    }

    @Override
    public void updateReviewByUserUuid(String userUuid, ReviewDto updatedReviewDto) {
        Review existingReview = reviewRepository.findByUserUuid(userUuid);
        if (existingReview != null) {
            existingReview.setRating(updatedReviewDto.getRating());
            existingReview.setComment(updatedReviewDto.getComment());
            reviewRepository.save(existingReview);
        }
    }

    @Override
    public void updateReviewByProductUuid(String productUuid, ReviewDto updatedReviewDto) {
        Review existingReview = reviewRepository.findByProductUuid(productUuid);
        if (existingReview != null) {
            existingReview.setRating(updatedReviewDto.getRating());
            existingReview.setComment(updatedReviewDto.getComment());
            reviewRepository.save(existingReview);
        }
    }

    @Override
    public void deleteReviewByUserUuid(String userUuid) {
        Review existingReview = reviewRepository.findByUserUuid(userUuid);
        if (existingReview != null) {
            reviewRepository.delete(existingReview);
        }
    }

    @Override
    public void deleteReviewByProductUuid(String productUuid) {
        Review existingReview = reviewRepository.findByProductUuid(productUuid);
        if (existingReview != null) {
            reviewRepository.delete(existingReview);
        }
    }
}