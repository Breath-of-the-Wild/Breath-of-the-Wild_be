package com.breath_of_the_wild_be.service.reviewService;

import com.breath_of_the_wild_be.dto.request.review.ReviewRequestDTO;
import com.breath_of_the_wild_be.dto.response.review.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {



        ReviewResponseDTO createReview(ReviewRequestDTO requestDTO);
        List<ReviewResponseDTO> getAllReviews();
        List<ReviewResponseDTO> getReviewsByContentId(Long contentId);
        List<ReviewResponseDTO> getReviewsByEmail(String email);


        void deleteReview(Long reviewId);
    }
