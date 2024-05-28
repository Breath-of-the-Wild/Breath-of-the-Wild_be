//package com.breath_of_the_wild_be.Utill;
//
//import com.breath_of_the_wild_be.service.campingService.CampingService;
//import com.breath_of_the_wild_be.service.campingService.CampingServiceImpl;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//
//
//@Component
//public class CampingInit {
//    private final CampingService campingService;
//
//    @Autowired
//    public CampingInit(CampingServiceImpl campingService) {
//        this.campingService = campingService;
//    }
//
//   @PostConstruct
//    public void initializeData() {
//        campingService.fetchAndSaveData();
//    }
//}