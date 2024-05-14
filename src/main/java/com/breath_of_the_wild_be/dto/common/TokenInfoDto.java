package com.breath_of_the_wild_be.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenInfoDto {

  private String accessToken;
  private String refreshToken;
}
