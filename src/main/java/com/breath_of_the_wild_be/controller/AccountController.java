package com.breath_of_the_wild_be.controller;

import com.breath_of_the_wild_be.dto.common.ResponseDto;
import com.breath_of_the_wild_be.util.ResponseCode;
import com.breath_of_the_wild_be.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

  @PostMapping("/test")
  public ResponseDto<Object> test(@RequestBody Map<String, Object> param) {
    return ResponseUtil.SUCCESS(ResponseCode.REQUEST_SUCCESS, param);
  }
}
