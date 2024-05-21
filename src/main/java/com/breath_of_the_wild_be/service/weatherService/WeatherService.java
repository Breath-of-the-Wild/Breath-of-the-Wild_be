package com.breath_of_the_wild_be.service.weatherService;

import com.breath_of_the_wild_be.domain.Weather;
import com.breath_of_the_wild_be.dto.request.weahter.DateRangeRequest;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;

public interface WeatherService {
  public void printWeatherDataFromApi();

  public List<Weather> getWeatherByDateRange(String startDate, String endDate);

  public Map<Integer, Integer> aggregateWeatherData(@RequestBody DateRangeRequest dateRangeRequest);




}