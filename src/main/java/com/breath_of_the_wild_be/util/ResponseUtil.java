package com.breath_of_the_wild_be.util;

import com.breath_of_the_wild_be.dto.common.ResponseDto;


public class ResponseUtil {
  public static <T> ResponseDto<T> SUCCESS(ResponseCode responseCode) {
    return new ResponseDto<>(responseCode.getStatus(), responseCode.getMsg());
  }
  public static <T> ResponseDto<T> SUCCESS(ResponseCode responseCode, T data) {
    return new ResponseDto<>(responseCode.getStatus(), responseCode.getMsg(), data);
  }

  public static <T> ResponseDto<T> FAILURE(ResponseCode responseCode) {
    return new ResponseDto<>(responseCode.getStatus(), responseCode.getMsg());
  }

  public static <T> ResponseDto<T> FAILURE(ResponseCode responseCode, String string) {
    return new ResponseDto<>(responseCode.getStatus(), responseCode.getMsg(string));
  }

  public static <T> ResponseDto<T> FAILURE(ResponseCode responseCode, String[] strings) {
    return new ResponseDto<>(responseCode.getStatus(), responseCode.getMsg(strings));
  }

  public static <T> ResponseDto<T> ERROR(ResponseCode responseCode) {
    return new ResponseDto<>(responseCode.getStatus(), responseCode.getMsg());
  }

  public static <T> ResponseDto<T> ERROR(ResponseCode responseCode, String string) {
    return new ResponseDto<>(responseCode.getStatus(), responseCode.getMsg(string));
  }

  public static <T> ResponseDto<T> ERROR(ResponseCode responseCode, String[] strings) {
    return new ResponseDto<>(responseCode.getStatus(), responseCode.getMsg(strings));
  }

  public static String getErrorResponseJson(Integer status, String msg) {
    return
      "{" +
        "\"status\": " + status + "," +
        "\"message\": " + "\"" + msg + "\"," +
        "\"data\": " + "null" +
      "}";
  }
}
