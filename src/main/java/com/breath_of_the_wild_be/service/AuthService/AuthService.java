package com.breath_of_the_wild_be.service.AuthService;

import com.breath_of_the_wild_be.dto.account.SignInDto;
import com.breath_of_the_wild_be.dto.account.SignUpDto;
import com.breath_of_the_wild_be.dto.common.ResponseDto;

public interface AuthService {

  ResponseDto signUp(SignUpDto signUpDto);

  ResponseDto signIn(SignInDto signInDto);

  ResponseDto generateAccessToken(String token);
}
