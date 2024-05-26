package com.breath_of_the_wild_be.service.camplikeService;


import com.breath_of_the_wild_be.dto.response.CampLikeResponseDTO;
import com.breath_of_the_wild_be.dto.request.CampLikeRequestDTO;

import java.util.List;

public interface CampLikeService {
    void likeCamping(CampLikeRequestDTO requestDto);
    void unlikeCamping(CampLikeRequestDTO requestDto);
    List<CampLikeResponseDTO> getLikedCampsByUser(String email);

    boolean checkIfLiked(CampLikeRequestDTO requestDto);
}
