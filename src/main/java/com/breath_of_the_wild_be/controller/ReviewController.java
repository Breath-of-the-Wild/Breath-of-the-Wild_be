package com.breath_of_the_wild_be.controller;

import com.breath_of_the_wild_be.dto.request.review.ReviewRequestDTO;
import com.breath_of_the_wild_be.dto.response.review.ReviewResponseDTO;
import com.breath_of_the_wild_be.service.reviewService.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<ReviewResponseDTO> createReview(@RequestBody ReviewRequestDTO requestDTO) {
        ReviewResponseDTO responseDTO = reviewService.createReview(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReviewResponseDTO>> getAllReviews() {
        List<ReviewResponseDTO> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/content/{contentId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByContentId(@PathVariable Long contentId) {
        List<ReviewResponseDTO> reviews = reviewService.getReviewsByContentId(contentId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByEmail(@PathVariable String email) {
        List<ReviewResponseDTO> reviews = reviewService.getReviewsByEmail(email);
        return ResponseEntity.ok(reviews);
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}
