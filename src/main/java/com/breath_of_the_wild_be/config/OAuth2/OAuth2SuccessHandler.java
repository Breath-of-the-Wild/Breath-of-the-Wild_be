package com.breath_of_the_wild_be.config.OAuth2;

import com.breath_of_the_wild_be.security.jwt.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String registrationId = getRegistrationId(authentication);
        String email = getEmail(oAuth2User, registrationId);
        String name = getName(oAuth2User, registrationId);

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        UserDetails userDetails = User.builder()
                .username(email)
                .password("") // OAuth2 로그인 시 비밀번호는 의미 없음
                .authorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")))
                .build();

        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);

        // 쿠키에 JWT 토큰 추가
        response.addCookie(createCookie("access_token", jwtToken, 3600));
        response.addCookie(createCookie("refresh_token", refreshToken, 604800));
        response.addCookie(createCookie("name", name, 3600));
        response.addCookie(createCookie("email", email, 3600));

        response.sendRedirect("http://breathofthewildfe.s3-website.ap-northeast-2.amazonaws.com/oauth2");
    }

    private Cookie createCookie(String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        return cookie;
    }

    private String getRegistrationId(Authentication authentication) {
        return ((OAuth2User) authentication.getPrincipal()).getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(auth -> auth.startsWith("ROLE_OAUTH2_"))
                .map(auth -> auth.substring("ROLE_OAUTH2_".length()))
                .findFirst()
                .orElse(null);
    }

    private String getEmail(OAuth2User oAuth2User, String registrationId) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        if ("kakao".equals(registrationId)) {
            // Return a random email value
            return "random-" + UUID.randomUUID().toString() + "@kakao.com";
        } else if ("naver".equals(registrationId)) {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");
            return (String) response.get("email");
        } else {
            return (String) attributes.get("email");
        }
    }

    private String getName(OAuth2User oAuth2User, String registrationId) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        if ("kakao".equals(registrationId)) {
            Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
            return (String) properties.get("nickname");
        } else if ("naver".equals(registrationId)) {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");
            return (String) response.get("name");
        } else {
            return (String) attributes.get("name");
        }
    }
}
