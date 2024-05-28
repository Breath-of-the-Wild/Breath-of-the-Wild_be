package com.breath_of_the_wild_be.service.weatherService;

import com.breath_of_the_wild_be.domain.RegionEnum;
import com.breath_of_the_wild_be.domain.Weather;
import com.breath_of_the_wild_be.dto.request.weahter.WeatherRequestDTO;
import com.breath_of_the_wild_be.dto.request.weahter.DateRangeRequest;
import com.breath_of_the_wild_be.dto.response.weather.WeatherResponseDTO;
import com.breath_of_the_wild_be.repository.WeatherRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Service
public class WeatherServiceImpl implements WeatherService {

  private final RestTemplate restTemplate;
  private final WeatherRepository weatherRepository;

  @Value("${openweathermap.apiKey}")
  private String apiKey;

  public WeatherServiceImpl(RestTemplate restTemplate, WeatherRepository weatherRepository) {
    this.restTemplate = restTemplate;
    this.weatherRepository = weatherRepository;
  }


  @Override
  public void printWeatherDataFromApi() {
    for (RegionEnum region : RegionEnum.values()) {
      String url = String.format(
          "https://api.openweathermap.org/data/2.5/forecast/daily?lat=%f&lon=%f&cnt=16&lang=kr&units=metric&appid=%s",
          region.getLatitude(), region.getLongitude(), apiKey);

      WeatherResponseDTO response = restTemplate.getForObject(url, WeatherResponseDTO.class);

      if (response != null) {
//        log.info("Weather Data: {}", response);
//        System.out.println("도시: " + response.getCity().getName());
//        System.out.println("국가: " + response.getCity().getCountry());

        for (WeatherResponseDTO.WeatherList weatherList : response.getList()) {
          WeatherRequestDTO weatherRequestDTO = new WeatherRequestDTO();

          //기본정보
          weatherRequestDTO.setCityId(response.getCity().getId());
          weatherRequestDTO.setCityName(response.getCity().getName());
          weatherRequestDTO.setLon(response.getCity().getCoord().getLon());
          weatherRequestDTO.setLat(response.getCity().getCoord().getLat());
          weatherRequestDTO.setCountry(response.getCity().getCountry());

          //날짜, 일출,일몰시간
          weatherRequestDTO.setDate(convertUnixToDateFormat(weatherList.getDt()));
          weatherRequestDTO.setSunrise(convertUnixToDateFormat(weatherList.getSunrise()));
          weatherRequestDTO.setSunset(convertUnixToDateFormat(weatherList.getSunset()));

          //기온
          weatherRequestDTO.setDayTemp(weatherList.getTemp().getDay());
          weatherRequestDTO.setMinTemp(weatherList.getTemp().getMin());
          weatherRequestDTO.setMaxTemp(weatherList.getTemp().getMax());
          weatherRequestDTO.setNightTemp(weatherList.getTemp().getNight());
          weatherRequestDTO.setEveTemp(weatherList.getTemp().getEve());
          weatherRequestDTO.setMornTemp(weatherList.getTemp().getMorn());

          //체감온도
          weatherRequestDTO.setDayFeelsTemp(weatherList.getFeels_like().getDay());
          weatherRequestDTO.setNightFeelsTemp(weatherList.getFeels_like().getNight());
          weatherRequestDTO.setEveFeelsTemp(weatherList.getFeels_like().getEve());
          weatherRequestDTO.setMornFeelsTemp(weatherList.getFeels_like().getMorn());

          //기압, 습도
          weatherRequestDTO.setPressure(weatherList.getPressure());
          weatherRequestDTO.setHumidity(weatherList.getHumidity());

          //풍속 풍향 구름 강수확률
          weatherRequestDTO.setSpeed(weatherList.getSpeed());
          weatherRequestDTO.setDeg(weatherList.getDeg());
          weatherRequestDTO.setGust(weatherList.getGust());
          weatherRequestDTO.setClouds(weatherList.getClouds());
          weatherRequestDTO.setPop(weatherList.getPop());
          weatherRequestDTO.setRain(weatherList.getRain());

          if (!weatherList.getWeather().isEmpty()) {
            WeatherResponseDTO.WeatherList.Weather weather = weatherList.getWeather().get(0);

            weatherRequestDTO.setWeatherStatusID(weather.getId());
            weatherRequestDTO.setWeatherStatus(weather.getMain());
            weatherRequestDTO.setWeatherStatusKr(weather.getDescription());
            weatherRequestDTO.setDescription(weather.getIcon());
          }

          Weather existingWeather = weatherRepository.findByCityIdAndDate(
              weatherRequestDTO.getCityId(), weatherRequestDTO.getDate());

          if (existingWeather != null) {
            updateExistingWeather(existingWeather, weatherRequestDTO);
          } else {
            Weather newWeather = convertToEntity(weatherRequestDTO);
            weatherRepository.save(newWeather);
          }
        }
      }
    }
  }


  private void updateExistingWeather(Weather existingWeather, WeatherRequestDTO weatherRequestDTO) {

    existingWeather.setSunrise(weatherRequestDTO.getSunrise());
    existingWeather.setSunset(weatherRequestDTO.getSunset());

    existingWeather.setDayTemp(weatherRequestDTO.getDayTemp());
    existingWeather.setMaxTemp(weatherRequestDTO.getMaxTemp());
    existingWeather.setMinTemp(weatherRequestDTO.getMinTemp());
    existingWeather.setNightTemp(weatherRequestDTO.getNightTemp());
    existingWeather.setEveTemp(weatherRequestDTO.getEveTemp());
    existingWeather.setMornTemp(weatherRequestDTO.getMornTemp());

    existingWeather.setHumidity(weatherRequestDTO.getHumidity());
    existingWeather.setPressure(weatherRequestDTO.getPressure());

    existingWeather.setDayFeelsTemp(weatherRequestDTO.getDayFeelsTemp());
    existingWeather.setNightFeelsTemp(weatherRequestDTO.getNightFeelsTemp());
    existingWeather.setEveFeelsTemp(weatherRequestDTO.getEveFeelsTemp());
    existingWeather.setMornFeelsTemp(weatherRequestDTO.getMornFeelsTemp());

    existingWeather.setWeatherStatusID(weatherRequestDTO.getWeatherStatusID());
    existingWeather.setWeatherStatus(weatherRequestDTO.getWeatherStatus());
    existingWeather.setWeatherStatusKr(weatherRequestDTO.getWeatherStatusKr());
    existingWeather.setDescription(weatherRequestDTO.getDescription());

    existingWeather.setSpeed(weatherRequestDTO.getSpeed());
    existingWeather.setDeg(weatherRequestDTO.getDeg());
    existingWeather.setGust(weatherRequestDTO.getGust());
    existingWeather.setClouds(weatherRequestDTO.getClouds());
    existingWeather.setPop(weatherRequestDTO.getPop());
    existingWeather.setRain(weatherRequestDTO.getRain());

    weatherRepository.save(existingWeather);
  }

  private Weather convertToEntity(WeatherRequestDTO weatherRequestDTO) {
    Weather weather = new Weather();
    weather.setCityId(weatherRequestDTO.getCityId());
    weather.setCityName(weatherRequestDTO.getCityName());
    weather.setLon(weatherRequestDTO.getLon());
    weather.setLat(weatherRequestDTO.getLat());
    weather.setCountry(weatherRequestDTO.getCountry());

    weather.setDate(weatherRequestDTO.getDate());
    weather.setSunrise(weatherRequestDTO.getSunrise());
    weather.setSunset(weatherRequestDTO.getSunset());

    weather.setDayTemp(weatherRequestDTO.getDayTemp());
    weather.setMaxTemp(weatherRequestDTO.getMaxTemp());
    weather.setMinTemp(weatherRequestDTO.getMinTemp());
    weather.setNightTemp(weatherRequestDTO.getNightTemp());
    weather.setEveTemp(weatherRequestDTO.getEveTemp());
    weather.setMornTemp(weatherRequestDTO.getMornTemp());

    weather.setDayFeelsTemp(weatherRequestDTO.getDayFeelsTemp());
    weather.setNightFeelsTemp(weatherRequestDTO.getNightFeelsTemp());
    weather.setEveFeelsTemp(weatherRequestDTO.getEveFeelsTemp());
    weather.setMornFeelsTemp(weatherRequestDTO.getMornFeelsTemp());

    weather.setHumidity(weatherRequestDTO.getHumidity());
    weather.setPressure(weatherRequestDTO.getPressure());

    weather.setWeatherStatusID(weatherRequestDTO.getWeatherStatusID());
    weather.setWeatherStatus(weatherRequestDTO.getWeatherStatus());
    weather.setWeatherStatusKr(weatherRequestDTO.getWeatherStatusKr());
    weather.setDescription(weatherRequestDTO.getDescription());

    weather.setSpeed(weatherRequestDTO.getSpeed());
    weather.setDeg(weatherRequestDTO.getDeg());
    weather.setGust(weatherRequestDTO.getGust());
    weather.setClouds(weatherRequestDTO.getClouds());
    weather.setPop(weatherRequestDTO.getPop());
    weather.setRain(weatherRequestDTO.getRain());

    return weather;
  }

  private String convertUnixToDateFormat(long unixSeconds) {
    Date date = new Date(unixSeconds * 1000L);
    SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd");
    jdf.setTimeZone(TimeZone.getTimeZone("GMT+9"));
    return jdf.format(date);
  }

  @Override
  public List<Weather> getWeatherByDateRange(String startDate, String endDate) {

    return weatherRepository.findWeatherByDate(startDate, endDate);
  }

  @Override
  public Map<Integer, Integer> aggregateWeatherData(DateRangeRequest dateRangeRequest) {
    String startDate = dateRangeRequest.getStartDate();
    String endDate = dateRangeRequest.getEndDate();

    List<Weather> weatherData = weatherRepository.findWeatherByDate(startDate, endDate);

    Map<Integer, Map<String, Double>> aggregatedData = aggregateWeather(weatherData);
    Map<Integer, Double> scores = calculateScores(aggregatedData);
    Map<Integer, Integer> rankedCities = rankCities(scores);

    return rankedCities;
  }



  private Map<Integer, Map<String, Double>> aggregateWeather(List<Weather> weatherDataList) {
    Map<Integer, Map<String, Double>> aggregatedData = new HashMap<>();

    for (Weather data : weatherDataList) {
      int cityId = data.getCityId();

      aggregatedData.putIfAbsent(cityId, new HashMap<>());
      Map<String, Double> cityData = aggregatedData.get(cityId);

      cityData.put("temp",
          cityData.getOrDefault("temp", 0.0) + calculateTemperatureScore(data.getMaxTemp(),
              data.getMinTemp()));
      cityData.put("pop",
          cityData.getOrDefault("pop", 0.0) + calculateElementScore(data.getPop(), 0.2));
      cityData.put("rain",
          cityData.getOrDefault("rain", 0.0) + calculateElementScore(data.getRain(), 0.2));
      cityData.put("speed",
          cityData.getOrDefault("speed", 0.0) + calculateElementScore(data.getSpeed(), 0.2));
      cityData.put("humidity",
          cityData.getOrDefault("humidity", 0.0) + calculateElementScore(data.getHumidity(), 0.2));
      cityData.put("clouds",
          cityData.getOrDefault("clouds", 0.0) + calculateElementScore(data.getClouds(), 0.2));
    }

    return aggregatedData;
  }

  private double calculateTemperatureScore(double maxTemp, double minTemp) {
    double idealMaxTemp = 25.0;
    double idealMinTemp = 15.0;
    return (Math.abs(idealMaxTemp - maxTemp) + Math.abs(idealMinTemp - minTemp)) / 2.0;
  }

  private double calculateElementScore(double value, double weight) {
    return (100 - value) * weight;
  }

  private Map<Integer, Double> calculateScores(Map<Integer, Map<String, Double>> aggregatedData) {
    Map<Integer, Double> scores = new HashMap<>();

    for (Map.Entry<Integer, Map<String, Double>> entry : aggregatedData.entrySet()) {
      Integer cityId = entry.getKey();
      Map<String, Double> elements = entry.getValue();
      double score =
          elements.get("pop") + elements.get("rain") + elements.get("humidity") + elements.get(
              "speed") + elements.get("clouds") - elements.get("temp");
      scores.put(cityId, score);
    }

    return scores;
  }

  private Map<Integer, Integer> rankCities(Map<Integer, Double> scores) {
    List<Map.Entry<Integer, Double>> sortedEntries = scores.entrySet().stream()
        .sorted(Map.Entry.<Integer, Double>comparingByValue().reversed())
        .collect(Collectors.toList());

    Map<Integer, Integer> rankedCities = new LinkedHashMap<>();
    for (int i = 0; i < sortedEntries.size(); i++) {
      rankedCities.put(i + 1, sortedEntries.get(i).getKey());
    }

    return rankedCities;
  }

}