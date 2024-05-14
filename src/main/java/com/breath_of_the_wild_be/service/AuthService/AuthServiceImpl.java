package com.breath_of_the_wild_be.service.AuthService;

import com.breath_of_the_wild_be.config.JwtTokenProvider;
import com.breath_of_the_wild_be.dto.account.SignInDto;
import com.breath_of_the_wild_be.dto.account.SignUpDto;
import com.breath_of_the_wild_be.dto.common.ResponseDto;
import com.breath_of_the_wild_be.dto.common.TokenInfoDto;
import com.breath_of_the_wild_be.domain.account.AccountEntity;
import com.breath_of_the_wild_be.domain.account.AccountRole;
import com.breath_of_the_wild_be.exception.CustomException;
import com.breath_of_the_wild_be.repository.AccountRepository;
import com.breath_of_the_wild_be.util.ResponseCode;
import com.breath_of_the_wild_be.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AccountRepository accountRepository;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final JwtTokenProvider jwtTokenProvider;
  private final PasswordEncoder passwordEncoder;

  @Override
  public ResponseDto signUp(SignUpDto signUpDto) {
    try {
      AccountEntity account = AccountEntity
        .builder()
        .id(signUpDto.getId())
        .password(passwordEncoder.encode(signUpDto.getPassword()))
        .role(AccountRole.USER.name())
        .build();

      accountRepository.save(account);
    } catch (Exception e) {
      throw new CustomException(ResponseCode.ACCOUNT_SIGN_UP_FAIL);
    }

    return ResponseUtil.SUCCESS(ResponseCode.ACCOUNT_SIGN_UP_SUCCESS, signUpDto);
  }

  @Override
  public ResponseDto signIn(SignInDto signInDto) {
    try {
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(signInDto.getId(), signInDto.getPassword());
      Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

      SecurityContextHolder.getContext().setAuthentication(authentication);
      TokenInfoDto jwt = jwtTokenProvider.generateToken(authentication);

      return ResponseUtil.SUCCESS(ResponseCode.ACCOUNT_SIGN_IN_SUCCESS, jwt);
    } catch (Exception e) {
      return ResponseUtil.ERROR(ResponseCode.ACCOUNT_SIGN_IN_FAIL);
    }
  }

  @Override
  public ResponseDto generateAccessToken(String token) {
    try {
      TokenInfoDto tokenInfoDto = jwtTokenProvider.generateAccessToken(token);
      return ResponseUtil.SUCCESS(ResponseCode.JWT_ACCESS_TOKEN_GENERATE_SUCCESS, tokenInfoDto);
    } catch (Exception e) {
      return ResponseUtil.ERROR(ResponseCode.JWT_ACCESS_TOKEN_GENERATE_FAIL);
    }
  }
}
