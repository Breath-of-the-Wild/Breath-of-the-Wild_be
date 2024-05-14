package com.breath_of_the_wild_be.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class SignUpDto {

  @NotBlank(message = "ID")
  private String id;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  private String username;
  private String birth;
}
