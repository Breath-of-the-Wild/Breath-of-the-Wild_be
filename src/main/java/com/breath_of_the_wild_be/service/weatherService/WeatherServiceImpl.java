package com.breath_of_the_wild_be.service.weatherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import lombok.extern.log4j.Log4j2;
import com.breath_of_the_wild_be.domain.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
      try {
        // API 요청 URL 생성
        String url = "https://api.openweathermap.org/data/2.5/forecast/daily" +
            "?lat=37.49015&lon=127.24815&cnt=16&lang=kr&units=metric&appid=" + apiKey;

        // ObjectMapper 생성
        ObjectMapper objectMapper = new ObjectMapper();

        // API 요청 및 JSON 데이터 읽어오기
        Weather weather = objectMapper.readValue(new URL(url), Weather.class);

        // 콘솔에 출력
        log.info("Weather Data: {}", weather);
      } catch (IOException e) {
        log.error("Error while fetching weather data: {}", e.getMessage());
      }
  }
}
