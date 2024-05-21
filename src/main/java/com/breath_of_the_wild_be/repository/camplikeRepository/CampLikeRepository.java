package com.breath_of_the_wild_be.repository.camplikeRepository;

import com.breath_of_the_wild_be.domain.CampLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampLikeRepository extends JpaRepository<CampLike, Long> {
    List<CampLike> findByMemberemail(String memberemail);
    List<CampLike> findByCampId(Long campId);


}