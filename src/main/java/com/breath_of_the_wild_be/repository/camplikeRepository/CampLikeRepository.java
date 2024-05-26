package com.breath_of_the_wild_be.repository.camplikeRepository;

import com.breath_of_the_wild_be.domain.CampLike;
import com.breath_of_the_wild_be.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


import com.breath_of_the_wild_be.domain.Camping;


public interface CampLikeRepository extends JpaRepository<CampLike, Long> {
    Optional<CampLike> findByCampingAndMember(Camping camping, Member member);
    List<CampLike> findByMember(Member member);
}
