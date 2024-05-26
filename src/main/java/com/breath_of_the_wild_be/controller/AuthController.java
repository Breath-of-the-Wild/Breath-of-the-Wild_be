package com.breath_of_the_wild_be.controller;

import com.breath_of_the_wild_be.domain.RefreshToken;
import com.breath_of_the_wild_be.dto.response.member.MemberTokenDto;
import com.breath_of_the_wild_be.repository.RefreshTokenRepository;
import com.breath_of_the_wild_be.security.jwt.JwtTokenUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenUtil jwtTokenUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    @PostMapping("/refresh")
    public ResponseEntity<MemberTokenDto> refreshAccessToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = null;

        // 쿠키에서 리프레시 토큰을 가져옴
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("refresh_token")) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }

        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // 리프레시 토큰을 DB에서 찾음
        Optional<RefreshToken> refreshTokenOptional = refreshTokenRepository.findByToken(refreshToken);
        if (refreshTokenOptional.isEmpty() || jwtTokenUtil.isTokenExpired(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        RefreshToken refreshTokenEntity = refreshTokenOptional.get();
        String username = refreshTokenEntity.getUsername();

        // 새로운 액세스 토큰 발급
        String newAccessToken = jwtTokenUtil.generateToken(username);

        // 새로운 액세스 토큰 쿠키 설정
        Cookie accessTokenCookie = new Cookie("access_token", newAccessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(true); // HTTPS에서만 사용
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(jwtTokenUtil.getExpiryDuration(newAccessToken));
        response.addCookie(accessTokenCookie);

        return ResponseEntity.status(HttpStatus.OK).body(new MemberTokenDto(username, username, newAccessToken, refreshToken));
    }
}
