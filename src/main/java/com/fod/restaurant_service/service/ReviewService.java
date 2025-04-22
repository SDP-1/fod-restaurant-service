package com.fod.restaurant_service.service;

import com.fod.restaurant_service.dto.ReviewRequestDTO;
import com.fod.restaurant_service.entity.Review;
import com.fod.restaurant_service.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Review createReview(ReviewRequestDTO dto) {
        Review review = modelMapper.map(dto, Review.class);
        review.setCreatedAt(new Date());
        review.setUpdatedAt(new Date());
        review.setActive(true);
        return reviewRepository.save(review);
    }

    public Review updateReview(String id, ReviewRequestDTO dto) {
        Review existing = getReviewById(id);
        modelMapper.map(dto, existing); // maps non-null values
        existing.setUpdatedAt(new Date());
        return reviewRepository.save(existing);
    }

    public void deleteReview(String id) {
        Review review = getReviewById(id);
        review.setActive(false);
        review.setUpdatedAt(new Date());
        reviewRepository.save(review);
    }

    public Review getReviewById(String id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
    }

    public List<Review> getReviewsByTargetId(String targetId) {
        return reviewRepository.findByTargetIdAndIsActiveTrue(targetId);
    }

    public List<Review> getReviewsByUserId(String userId) {
        return reviewRepository.findByUserId(userId);
    }
}

