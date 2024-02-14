package com.archisacademy.ecommercespringboot.service.impl;

import com.archisacademy.ecommercespringboot.dto.ReviewDto;
import com.archisacademy.ecommercespringboot.model.Review;
import com.archisacademy.ecommercespringboot.repository.ReviewRepository;
import com.archisacademy.ecommercespringboot.service.ReviewService;
import com.archisacademy.ecommercespringboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
        Review review = new Review();
        review.setUuid(reviewDto.getUuid());
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setCreatedAt(new Date()); // Şu anki tarih ve zamanı ayarlar

        // UserService ve ProductService aracılığıyla User ve Product nesnelerini alınabilir
        // Ancak burada bunun nasıl yapıldığını bilmemekteyim
        // Bu yüzden burada User ve Product nesneleri reviewDto'dan alınmış gibi varsayılmıştır
        // Gerçek uygulamada User ve Product nesnelerini ilgili hizmetlerden almak gerekir
        review.setUser(userService.getUserById(reviewDto.getUserId()));
        review.setProduct(productService.getProductById(reviewDto.getProductId()));

        return reviewRepository.save(review);    }

    @Override
    public List<ReviewDto> getAllReviewsForProduct(String productUuid) {
        List<Review> reviews = reviewRepository.findByProductUuid(productUuid);
        return convertToDtoList(reviews);
    }
    @Override
    public List<ReviewDto> getAllReviewsByUser(String userUuid) {
        List<Review> reviews = reviewRepository.findByUserUuid(userUuid);
        return convertToDtoList(reviews);
    }

    @Override
    public ReviewDto getReviewByUuid(String reviewUuid) {
        Review review = reviewRepository.findByUuid(reviewUuid);
        return convertToDto(review);
    }

    @Override
    public ReviewDto getReviewByUserAndProduct(String userUuid, String productUuid) {
        Review review = reviewRepository.findByUserUuidAndProductUuid(userUuid, productUuid);
        return convertToDto(review);
    }

    @Override
    public Review updateReview(String reviewUuid, ReviewDto updatedReviewDto) {
        Review review = reviewRepository.findByUuid(reviewUuid);
        if (review != null) {
            review.setRating(updatedReviewDto.getRating());
            review.setComment(updatedReviewDto.getComment());
            return reviewRepository.save(review);
        }
        return null;
    }

    @Override
    public void deleteReview(String reviewUuid) {
        Review review = reviewRepository.findByUuid(reviewUuid);
        if (review != null) {
            reviewRepository.delete(review);
        }
    }

    // Review nesnelerini ReviewDto nesnelerine dönüştürür
    private ReviewDto convertToDto(Review review) {
        if (review == null) {
            return null;
        }
        return new ReviewDto(review.getUuid(), review.getRating(), review.getComment(), review.getCreatedAt());
    }

    // Review listesini ReviewDto listesine dönüştürür
    private List<ReviewDto> convertToDtoList(List<Review> reviews) {
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        for (Review review : reviews) {
            reviewDtoList.add(convertToDto(review));
        }
        return reviewDtoList;
    }
}