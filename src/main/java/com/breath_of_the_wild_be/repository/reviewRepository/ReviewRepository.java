package com.breath_of_the_wild_be.repository.reviewRepository;

import com.breath_of_the_wild_be.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {


    List<Review> findByCamping_ContentId(Long contentId);
    List<Review> findByMember_Email(String email);
}
