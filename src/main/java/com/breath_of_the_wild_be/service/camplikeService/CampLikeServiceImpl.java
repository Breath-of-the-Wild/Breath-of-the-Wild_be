package com.breath_of_the_wild_be.service.camplikeService;

import com.breath_of_the_wild_be.domain.CampLike;
import com.breath_of_the_wild_be.repository.camplikeRepository.CampLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampLikeServiceImpl implements CampLikeService {

    private final CampLikeRepository campLikeRepository;

    @Autowired
    public CampLikeServiceImpl(CampLikeRepository campLikeRepository) {
        this.campLikeRepository = campLikeRepository;
    }

    @Override
    public CampLike saveCampLike(CampLike campLike) {
        return campLikeRepository.save(campLike);
    }

    @Override
    public List<CampLike> getCampLikesByMno(String memberemail) {
        return campLikeRepository.findByMemberemail(memberemail);
    }

    @Override
    public List<CampLike> getCampLikesByCampId(Long campId) {
        return campLikeRepository.findByCampId(campId);
    }

    @Override
    public void deleteCampLike(Long campLikeId) {
        campLikeRepository.deleteById(campLikeId);
    }
}