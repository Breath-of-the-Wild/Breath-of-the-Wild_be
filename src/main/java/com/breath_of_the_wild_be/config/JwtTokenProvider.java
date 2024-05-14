package com.breath_of_the_wild_be.config;

import com.breath_of_the_wild_be.dto.common.TokenInfoDto;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Log4j2
public class JwtTokenProvider {

  @Value(value = "${app.jwtSecret}")
  private String jwtSecret; // 암호화 키

  @Value(value = "${app.jwtAccessExpirationInMs}")
  private int jwtAccessExpirationInMs; // Access Token 만료일

  @Value(value = "${app.jwtRefreshExpirationInMs}")
  private int jwtRefreshExpirationInMs; // Refresh Toekn 만료일

  public TokenInfoDto generateToken(Authentication authentication) {
    CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

    String authorities = customUserDetails.getAuthorities().stream()
      .map(GrantedAuthority::getAuthority)
      .collect(Collectors.joining(","));
    Date now = new Date();
    Date accessTokenExpireTime = new Date(now.getTime() + jwtAccessExpirationInMs);
    Date refreshTokenExpireTime = new Date(now.getTime() + jwtRefreshExpirationInMs);

    String accessToken = Jwts.builder()
      .setSubject(customUserDetails.getIdx().toString())
      .claim("auth", authorities)
      .claim("type", "access")
      .setIssuedAt(new Date())
      .setExpiration(accessTokenExpireTime)
      .signWith(SignatureAlgorithm.HS512, jwtSecret)
      .compact();

    String refreshToken = Jwts.builder()
      .setSubject(customUserDetails.getIdx().toString())
      .claim("auth", authorities)
      .claim("type", "refresh")
      .setIssuedAt(new Date())
      .setExpiration(refreshTokenExpireTime)
      .signWith(SignatureAlgorithm.HS512, jwtSecret)
      .compact();

    return TokenInfoDto.builder()
      .accessToken(accessToken)
      .refreshToken(refreshToken)
      .build();
  }

  public TokenInfoDto generateAccessToken(String token) throws Exception {
    validateRefreshToken(token);

    Authentication authentication = getAuthentication(token);
    CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

    String authorities = customUserDetails.getAuthorities().stream()
      .map(GrantedAuthority::getAuthority)
      .collect(Collectors.joining(","));
    Date now = new Date();
    Date accessTokenExpireTime = new Date(now.getTime() + jwtAccessExpirationInMs);

    String accessToken = Jwts.builder()
      .setSubject(customUserDetails.getIdx().toString())
      .claim("auth", authorities)
      .claim("type", "access")
      .setIssuedAt(new Date())
      .setExpiration(accessTokenExpireTime)
      .signWith(SignatureAlgorithm.HS512, jwtSecret)
      .compact();

    return TokenInfoDto.builder()
      .accessToken(accessToken)
      .build();
  }

  public Authentication getAuthentication(String token) {
    // 토큰 복호화
    Claims claims = parseClaims(token);

    if (claims.get("auth") == null) {
        throw new JwtException("Invalid JWT Token");
    }

    // 클레임에서 권한 정보 가져오기
    Collection<? extends GrantedAuthority> authorities =
      Arrays.stream(claims.get("auth").toString().split(","))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());

    // UserDetails 객체를 만들어서 Authentication 리턴
    UserDetails principal = new CustomUserDetails(Long.parseLong(claims.getSubject()), "", "", authorities);
    return new UsernamePasswordAuthenticationToken(principal, "", authorities);
  }

  private Claims parseClaims(String token) {
    try {
      return Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody();
    } catch (ExpiredJwtException e) {
      return e.getClaims();
    }
  }

  public boolean validateToken(String token) throws IOException {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return true;
    } catch (SignatureException ex) {
      log.error("Invalid JWT signature");
      throw new JwtException("Invalid JWT signature");
    } catch (MalformedJwtException ex) {
      log.error("Invalid JWT token");
      throw new JwtException("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      log.error("Expired JWT token");
      throw new JwtException("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      log.error("Unsupported JWT token");
      throw new JwtException("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      log.error("JWT claims string is empty");
      throw new JwtException("JWT claims string is empty");
    }
  }

  public boolean validateRefreshToken(String refreshToken) throws Exception {
    validateToken(refreshToken);
    try {
      String type = parseClaims(refreshToken).get("type").toString();

      if(!Objects.equals(type, "refresh")) {
        log.error("Invalid JWT Refresh Token");
        throw new Exception("Invalid JWT Refresh Token");
      }
      return true;
    } catch (Exception e) {
      throw new JwtException(e.getMessage());
    }
  }
}
