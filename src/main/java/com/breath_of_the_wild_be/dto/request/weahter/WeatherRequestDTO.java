package com.breath_of_the_wild_be.dto.request.weahter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherRequestDTO {
    private Integer cityId;
    private String cityName;
    private double lon;
    private double lat;
    private String country;

    private String date;
    private String sunrise;
    private String sunset;


    private double dayTemp;
    private double minTemp;
    private double maxTemp;
    private double nightTemp;
    private double eveTemp;
    private double mornTemp;


    private double dayFeelsTemp;
    private double nightFeelsTemp;
    private double eveFeelsTemp;
    private double mornFeelsTemp;

    private Integer pressure;
    private Integer humidity;


    private Integer weatherStatusID;  //
    private String weatherStatus; //main
    private String weatherStatusKr; //
    private String description;


    private double speed;
    private int deg;
    private double gust;
    private int clouds;
    private double pop;
    private double rain;
}