package com.breath_of_the_wild_be.domain;

import lombok.Getter;

@Getter
public enum RegionEnum {
  SEOUL_GYEONGGI(1, "서울경기", 127.24815, 37.49015),
  CHUNGCHEONG(2, "충청도", 127.2501, 36.6596),
  GYEONGSANG(3, "경상도", 127.0676, 35.2923),
  GANGWON(4, "강원도", 128.1555, 37.8228),
  JEOLLA(5, "전라도", 127.4938, 35.6633),
  JEJU(6, "제주도", 126.4983, 33.4890);

  private final int id;
  private final String doName;
  private final double longitude;
  private final double latitude;

  RegionEnum(int id, String doName, double longitude, double latitude) {
    this.id = id;
    this.doName = doName;
    this.longitude = longitude;
    this.latitude = latitude;
  }

}
