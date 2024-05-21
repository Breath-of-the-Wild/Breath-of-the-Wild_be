package com.breath_of_the_wild_be.service.camplikeService;

import com.breath_of_the_wild_be.domain.CampLike;

import java.util.List;

public interface CampLikeService {
    CampLike saveCampLike(CampLike campLike);
    List<CampLike> getCampLikesByMno(String memberemail);
    List<CampLike> getCampLikesByCampId(Long campId);
    void deleteCampLike(Long campLikeId);
}