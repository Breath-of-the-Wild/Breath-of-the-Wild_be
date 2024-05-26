package com.breath_of_the_wild_be.service.reviewService;

import com.breath_of_the_wild_be.domain.Review;
import com.breath_of_the_wild_be.domain.Camping;
import com.breath_of_the_wild_be.domain.Member;
import com.breath_of_the_wild_be.dto.request.review.ReviewRequestDTO;
import com.breath_of_the_wild_be.dto.response.review.ReviewResponseDTO;
import com.breath_of_the_wild_be.repository.campingRepository.CampingRepository;
import com.breath_of_the_wild_be.repository.memberRepository.MemberRepository;
import com.breath_of_the_wild_be.repository.reviewRepository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CampingRepository campingRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public ReviewResponseDTO createReview(ReviewRequestDTO requestDTO) {
        Camping camping = campingRepository.findByContentId(requestDTO.getContentId())
                .orElseThrow(() -> new RuntimeException("Camping not found"));
        Member member = memberRepository.findByEmail(requestDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Review review = new Review();
        review.setTitle(requestDTO.getTitle());
        review.setContent(requestDTO.getContent());
        review.setImageFile(requestDTO.getImageFile());
        review.setCreatedBy(requestDTO.getCreatedBy());
        review.setCamping(camping);
        review.setMember(member);

        Review savedReview = reviewRepository.save(review);
        return convertToDTO(savedReview);
    }

    @Override
    public List<ReviewResponseDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByContentId(Long contentId) {
        List<Review> reviews = reviewRepository.findByCamping_ContentId(contentId);
        return reviews.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

        @Override
        public List<ReviewResponseDTO> getReviewsByEmail(String email) {
            List<Review> reviews = reviewRepository.findByMember_Email(email);
            return reviews.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        }

    @Override
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    private ReviewResponseDTO convertToDTO(Review review) {
        ReviewResponseDTO responseDTO = new ReviewResponseDTO();
        responseDTO.setReviewId(review.getReviewId());
        responseDTO.setTitle(review.getTitle());
        responseDTO.setContent(review.getContent());
        responseDTO.setImageFile(review.getImageFile());
        responseDTO.setLikeCount(review.getLikeCount());
        responseDTO.setRateCount(review.getRateCount());
        responseDTO.setCreatedDate(review.getCreatedDate());
        responseDTO.setCreatedBy(review.getCreatedBy());
        responseDTO.setContentId(review.getCamping().getCampid());
        responseDTO.setEmail(review.getMember().getEmail());
        responseDTO.setFacltNm(review.getCamping().getFacltNm());
        return responseDTO;
    }
}
