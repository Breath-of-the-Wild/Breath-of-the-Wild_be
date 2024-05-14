package com.breath_of_the_wild_be.service.weatherService;
import com.breath_of_the_wild_be.dto.WeatherResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Log4j2
@Service
public class WeatherServiceImpl implements WeatherService {

  private final RestTemplate restTemplate;

  @Value("${openweathermap.apiKey}")
  private String apiKey;

  public WeatherServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public void printWeatherDataFromApi() {
    String url = "https://api.openweathermap.org/data/2.5/forecast/daily" +
        "?lat=37.49015&lon=127.24815&cnt=16&lang=kr&units=metric&appid=" + apiKey;

    WeatherResponseDTO response = restTemplate.getForObject(url, WeatherResponseDTO.class);

    if (response != null) {
      log.info("Weather Data: {}", response);
      System.out.println("도시: " + response.getCity().getName());
      System.out.println("국가: " + response.getCity().getCountry());

      if (!response.getList().isEmpty()) {
        WeatherResponseDTO.WeatherList firstWeatherList = response.getList().get(0);
        System.out.println("날짜: " + convertUnixToDateFormat(firstWeatherList.getDt()));
        System.out.println("낮 기온: " + firstWeatherList.getTemp().getDay() + "℃");

        if (!firstWeatherList.getWeather().isEmpty()) {
          WeatherResponseDTO.WeatherList.Weather firstWeather = firstWeatherList.getWeather().get(0);
          System.out.println("날씨 상태: " + firstWeather.getMain());
          System.out.println("설명: " + firstWeather.getDescription());
        }
      }
    }
  }

  private String convertUnixToDateFormat(long unixSeconds) {
    // Convert seconds to milliseconds
    Date date = new Date(unixSeconds*1000L);
    // Format of the date
    SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
    jdf.setTimeZone(TimeZone.getTimeZone("GMT+9"));
    String javaDate = jdf.format(date);
    return javaDate;
  }
}
