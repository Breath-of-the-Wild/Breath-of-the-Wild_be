package com.breath_of_the_wild_be.service.festivalService;


import com.breath_of_the_wild_be.domain.Camping;
import com.breath_of_the_wild_be.domain.Festival;

import java.util.List;

public interface FestivalService {


    List<Festival> getAllFestivals();

    void fetchAndSaveData();


    Festival findByContentId(String contentId);

    List<Festival> searchFestival(String searchType, String searchValue);

}