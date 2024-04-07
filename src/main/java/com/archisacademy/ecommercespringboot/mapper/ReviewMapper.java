package com.archisacademy.ecommercespringboot.mapper;

import com.archisacademy.ecommercespringboot.dto.ReviewDto;
import com.archisacademy.ecommercespringboot.model.Review;

public interface ReviewMapper {
    ReviewDto convertToDto(Review review);
}
