package com.breath_of_the_wild_be.service.campingService;

import com.breath_of_the_wild_be.domain.Camping;
import com.breath_of_the_wild_be.domain.Festival;

import java.util.List;

public interface CampingService {
    List<Camping> getAllCampings();

    Camping getCampingById(Long id);

    Camping addCamping(Camping camping);

    Camping updateCamping(Camping camping);

    void deleteCamping(Long id);

    void fetchAndSaveData();

    List<Camping> searchCampings(String searchType, String searchValue);

    Camping findByContentId(Long contentId);

    List<Camping> getCampingListByRegion(String doNm);

}
