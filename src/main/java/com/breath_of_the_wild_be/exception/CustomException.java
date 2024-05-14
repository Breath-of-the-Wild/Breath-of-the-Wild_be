package com.breath_of_the_wild_be.exception;

import com.breath_of_the_wild_be.util.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {
  private final ResponseCode responseCode;
}
