package com.breath_of_the_wild_be.security.jwt;

import com.breath_of_the_wild_be.service.RefreshTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    @Value("${jwt.tokenExpirationTime}")
    private Long tokenExpirationTime;

    @Value("${jwt.refreshTokenExpirationTime}")
    private Long refreshTokenExpirationTime;

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private RefreshTokenService refreshTokenService;

    // JWT 토큰에서 사용자 이름을 추출１
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // JWT 토큰에서 만료 날짜를 추출
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // 시크릿 키를 사용하여 JWT 토큰에서 정보를 추출
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // 토큰이 만료되었는지 확인
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // 사용자에 대한 액세스 토큰 생성
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername(), tokenExpirationTime);
    }

    // 이메일을 사용하여 토큰을 생성하는 오버로드된 메서드
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, email, tokenExpirationTime);
    }

    // 사용자에 대한 리프레시 토큰 생성
    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        String token = doGenerateToken(claims, userDetails.getUsername(), refreshTokenExpirationTime);
        refreshTokenService.createRefreshToken(userDetails.getUsername(), token, new Date(System.currentTimeMillis() + refreshTokenExpirationTime));
        return token;
    }

    private String doGenerateToken(Map<String, Object> claims, String subject, Long expirationTime) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    // 토큰 유효성 검사
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // 토큰의 만료 시간 계산
    public int getExpiryDuration(String token) {
        Claims claims = getAllClaimsFromToken(token);
        Date expiration = claims.getExpiration();
        return (int) ((expiration.getTime() - System.currentTimeMillis()) / 1000);
    }

    public Long getRefreshTokenExpirationTime() {
        return refreshTokenExpirationTime;
    }
}
