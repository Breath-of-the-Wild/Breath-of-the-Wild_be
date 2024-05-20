//package com.breath_of_the_wild_be.Utill;
//
//import com.breath_of_the_wild_be.service.festivalService.FestivalService;
//import com.breath_of_the_wild_be.service.festivalService.FestivalServiceImpl;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class FestivalInit {
//    private final FestivalService festivalService;
//
//    @Autowired
//    public FestivalInit(FestivalServiceImpl festivalService) {
//        this.festivalService = festivalService;
//    }
//
//   @PostConstruct
//    public void initializeData() {
//        festivalService.fetchAndSaveData();
//    }
//}
