package com.breath_of_the_wild_be.service;

import java.util.HashMap;
import java.util.Map;

public class RegionMapperConfig {

  private static final Map<String, String> regionMap = new HashMap<>();

  static {
    regionMap.put("강원", "강원도");
    regionMap.put("경기", "경기도");
    regionMap.put("경남", "경상남도");
    regionMap.put("경북", "경상북도");
    regionMap.put("광주", "광주시");
    regionMap.put("대구", "대구시");
    regionMap.put("대전", "대전시");
    regionMap.put("부산", "부산시");
    regionMap.put("서울", "서울시");
    regionMap.put("세종", "세종시");
    regionMap.put("울산", "울산시");
    regionMap.put("인천", "인천시");
    regionMap.put("전남", "전라남도");
    regionMap.put("전북", "전라북도");
    regionMap.put("제주", "제주도");
    regionMap.put("충남", "충청남도");
    regionMap.put("충북", "충청북도");
  }

  public static String mapRegion(String region) {
    return regionMap.getOrDefault(region, region);
  }
}
