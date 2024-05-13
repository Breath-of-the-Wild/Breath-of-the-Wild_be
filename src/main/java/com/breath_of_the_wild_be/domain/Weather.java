package com.breath_of_the_wild_be.domain;
<<<<<<< HEAD
import java.util.List;
import lombok.Data;

@Data
public class Weather {
  private City city;
  private String cod;
  private double message;
  private int cnt;
  private List<Forecast> list;
}

@Data
class City {
  private int id;
  private String name;
  private Coord coord;
  private String country;
  private int population;
  private int timezone;
}

@Data
class Coord {
  private double lon;
  private double lat;
}

@Data
class Forecast {
  private long dt;
  private long sunrise;
  private long sunset;
  private Temperature temp;
  private Temperature feels_like;
  private int pressure;
  private int humidity;
  private List<WeatherInfo> weather;
  private double speed;
  private int deg;
  private double gust;
  private int clouds;
  private double pop;
  private double rain;
}

@Data
class Temperature {
  private double day;
  private double min;
  private double max;
  private double night;
  private double eve;
  private double morn;
}

@Data
class WeatherInfo {
  private int id;
  private String main;
  private String description;
  private String icon;
=======

public class Weather {
>>>>>>> ba7b9b2 (테스트)
}
