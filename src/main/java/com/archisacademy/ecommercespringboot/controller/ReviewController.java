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
        return new ResponseEntity<>(reviewService.saveReview(reviewDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ReviewDto> getReviewByUserAndProduct(
            @RequestParam("userUuid") String userUuid,
            @RequestParam("productUuid") String productUuid) {
        return new ResponseEntity<>(reviewService.getReviewByUserUuid(userUuid, productUuid), HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ReviewDto>> getReviewsByProduct(
            @RequestParam("productUuid") String productUuid) {
        return new ResponseEntity<>(reviewService.getReviewByProductUuid(productUuid), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateReviewByUserAndProduct(
            @RequestParam("userUuid") String userUuid,
            @RequestParam("productUuid") String productUuid,
            @RequestBody ReviewDto updatedReviewDto) {
        return new ResponseEntity<>(reviewService.updateReviewByUserUuid(userUuid, productUuid, updatedReviewDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteReviewByUserAndProduct(
            @RequestParam("userUuid") String userUuid,
            @RequestParam("productUuid") String productUuid) {
        return new ResponseEntity<>(reviewService.deleteReviewByUserUuid(userUuid, productUuid), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{reviewUuid}") // deleteReviewsByReviewUuid
    public ResponseEntity<String> deleteReviewsByReviewUuid(
            @PathVariable("reviewUuid") String reviewUuid) {
        return new ResponseEntity<>(reviewService.deleteReviewsByReviewUuid(reviewUuid), HttpStatus.OK);
    }

    @GetMapping("/approved")
    public ResponseEntity<List<ReviewDto>> getAllApprovedReviewsByProduct(
            @RequestParam("productUuid") String productUuid) {
        return new ResponseEntity<>(reviewService.getAllReviewsByProductUUID(productUuid), HttpStatus.OK);
    }

    @PutMapping("/approve")
    public ResponseEntity<String> approveReview(
            @RequestParam("reviewUuid") String reviewUuid) {
        return new ResponseEntity<>(reviewService.approveReview(reviewUuid), HttpStatus.OK);
    }
}
