package com.breath_of_the_wild_be.dto;

import lombok.Data;

import java.util.List;

@Data
public class WeatherResponseDTO {
    private City city;
    private String cod;
    private double message;
    private int cnt;
    private List<WeatherList> list;

    @Data
    public static class City {
      private int id;
      private String name;
      private Coord coord;
      private String country;
      private int population;
      private int timezone;

      @Data
      public static class Coord {
        private double lon;
        private double lat;
      }
    }

  @Data
    public static class WeatherList {
      private long dt;
      private long sunrise;
      private long sunset;
      private Temp temp;
      private FeelsLike feels_like;
      private int pressure;
      private int humidity;
      private List<Weather> weather;
      private double speed;
      private int deg;
      private double gust;
      private int clouds;
      private double pop;

    @Data
      public static class Temp {
        private double day;
        private double min;
        private double max;
        private double night;
        private double eve;
        private double morn;
      }

    @Data
      public static class FeelsLike {
        private double day;
        private double night;
        private double eve;
        private double morn;
      }

    @Data
      public static class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;
      }
    }
  }
