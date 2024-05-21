package com.breath_of_the_wild_be.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "tbl_weather")
public class Weather {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
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


  private Integer weatherStatusID;
  private String weatherStatus;
  private String weatherStatusKr;
  private String description;


  private double speed;
  private int deg;
  private double gust;
  private int clouds;
  private double pop;
  private double rain;
}