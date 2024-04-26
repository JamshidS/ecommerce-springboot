package com.archisacademy.ecommercespringboot.controller;

import com.archisacademy.ecommercespringboot.dto.ReviewDto;
import com.archisacademy.ecommercespringboot.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/save")
    public ResponseEntity<ReviewDto> saveReview(@RequestBody ReviewDto reviewDto) {
        ReviewDto savedReview = reviewService.saveReview(reviewDto);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ReviewDto> getReviewByUserAndProduct(
            @RequestParam("userUuid") String userUuid,
            @RequestParam("productUuid") String productUuid) {
        ReviewDto reviewDto = reviewService.getReviewByUserUuid(userUuid, productUuid);
        return new ResponseEntity<>(reviewDto, HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ReviewDto>> getReviewsByProduct(
            @RequestParam("productUuid") String productUuid) {
        List<ReviewDto> reviewDtos = reviewService.getReviewByProductUuid(productUuid);
        return new ResponseEntity<>(reviewDtos, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateReviewByUserAndProduct(
            @RequestParam("userUuid") String userUuid,
            @RequestParam("productUuid") String productUuid,
            @RequestBody ReviewDto updatedReviewDto) {
        String message = reviewService.updateReviewByUserUuid(userUuid, productUuid, updatedReviewDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteReviewByUserAndProduct(
            @RequestParam("userUuid") String userUuid,
            @RequestParam("productUuid") String productUuid) {
        String message = reviewService.deleteReviewByUserUuid(userUuid, productUuid);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{reviewUuid}") // deleteReviewsByReviewUuid
    public ResponseEntity<String> deleteReviewsByReviewUuid(
            @PathVariable("reviewUuid") String reviewUuid) {
        String message = reviewService.deleteReviewsByReviewUuid(reviewUuid);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/approved")
    public ResponseEntity<List<ReviewDto>> getAllApprovedReviewsByProduct(
            @RequestParam("productUuid") String productUuid) {
        List<ReviewDto> reviewDtos = reviewService.getAllReviewsByProductUUID(productUuid);
        return new ResponseEntity<>(reviewDtos, HttpStatus.OK);
    }

    @PutMapping("/approve")
    public ResponseEntity<String> approveReview(
            @RequestParam("reviewUuid") String reviewUuid) {
        String message = reviewService.approveReview(reviewUuid);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
