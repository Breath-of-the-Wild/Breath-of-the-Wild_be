package com.breath_of_the_wild_be.util;

public enum ResponseCode {

  ACCOUNT_SIGN_UP_SUCCESS(200, "Sign up success"),

  ACCOUNT_SIGN_UP_FAIL(500, "Sign up fail"),
  ACCOUNT_SIGN_IN_SUCCESS(200, "Sign in success"),
  ACCOUNT_SIGN_IN_FAIL(500, "Sign in fail"),

  REQUIRE_DATA_NULL(500, "[ $ ] are require values"),
  REQUEST_DATA_LENGTH_INVALID(500, "[ $ ] are invalided length"),
  REQUEST_DATA_ERROR(500, "Invalid data"),

  REQUEST_SUCCESS(200, "Success"),

  JWT_ACCESS_TOKEN_GENERATE_SUCCESS(200, "Generate access token success"),
  JWT_ACCESS_TOKEN_GENERATE_FAIL(500, "Generate access token fail"),
  ACCESS_DENIED(403, "Invalid JWT signature");

  private final int status;
  private final String msg;

  ResponseCode(int status, String msg) {
    this.status = status;
    this.msg = msg;
  }

  public int getStatus() {
    return this.status;
  }

  public String getMsg() {
    return this.msg;
  }

  public String getMsg(String customStr) {
    String str = this.msg;

    str = str.replace("$", customStr);

    return str;
  }
  public String getMsg(String[] customStr) {
    String str = this.msg;

    for(int i = 0; i < customStr.length; i++) {
      str = str.replace("$", customStr[i]);
    }

    return str;
  }
}
