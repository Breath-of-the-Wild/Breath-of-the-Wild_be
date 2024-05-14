package com.breath_of_the_wild_be.dto;

import lombok.Data;

@Data
public class WeatherRequestDTO {
  private double day;
  private String icon;
}
