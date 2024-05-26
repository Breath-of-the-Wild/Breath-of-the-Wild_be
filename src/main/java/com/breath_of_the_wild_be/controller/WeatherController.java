package com.breath_of_the_wild_be.controller;

import com.breath_of_the_wild_be.domain.Weather;
import com.breath_of_the_wild_be.dto.request.weahter.DateRangeRequest;
import com.breath_of_the_wild_be.repository.WeatherRepository;
import com.breath_of_the_wild_be.service.weatherService.WeatherService;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://breathofthewildfe.s3-website.ap-northeast-2.amazonaws.com")
@RestController
@RequestMapping("api/weather")
@RequiredArgsConstructor
@Slf4j
public class WeatherController {

    private final WeatherService weatherService;
    private final WeatherRepository weatherRepository;


//    @GetMapping("/")
//    public String fetchWeatherData() {
//        weatherService.printWeatherDataFromApi();
//        return "Weather data fetched successfully.";
//    }

//    @GetMapping("/list")
//    public ResponseEntity<List<Weather>> getAllWeather() {
//        List<Weather> weatherList = weatherRepository.findAll();
//        return ResponseEntity.ok(weatherList);
//    }

    @PostMapping("/list")
    public List<Weather> getWeatherByDateRange(@RequestBody DateRangeRequest dateRangeRequest) {
        return weatherService.getWeatherByDateRange(dateRangeRequest.getStartDate(), dateRangeRequest.getEndDate());
    }


    @PostMapping("/abc1")
    public Map<Integer, Integer> aggregateWeatherData(@RequestBody DateRangeRequest dateRangeRequest) {
        return weatherService.aggregateWeatherData(dateRangeRequest);
    }
}