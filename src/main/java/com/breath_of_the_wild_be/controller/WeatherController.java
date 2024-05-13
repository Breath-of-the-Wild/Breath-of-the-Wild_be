package com.breath_of_the_wild_be.controller;

<<<<<<< HEAD
import com.breath_of_the_wild_be.domain.Weather;
import com.breath_of_the_wild_be.service.weatherService.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {

  private final WeatherService weatherService;


  @GetMapping("/weather")
  public String fetchWeatherData() {
    weatherService.printWeatherDataFromApi();
    return "Weather data fetched successfully.";
  }
}
=======
public class WeatherController {
}
>>>>>>> ba7b9b2 (테스트)
