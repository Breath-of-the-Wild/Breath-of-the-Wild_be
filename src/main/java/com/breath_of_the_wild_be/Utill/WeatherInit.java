//package com.breath_of_the_wild_be.Utill;
//
//
//import com.breath_of_the_wild_be.service.weatherService.WeatherService;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//    @Component
//    public class WeatherInit {
//        private final WeatherService weatherService;
//
//        @Autowired
//        public WeatherInit(WeatherService weatherService) {
//            this.weatherService = weatherService;
//        }
//
//      @PostConstruct
//        public void init() {
//            weatherService.printWeatherDataFromApi();
//        }
//    }
