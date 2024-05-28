//package com.breath_of_the_wild_be.controller;
//
//import com.breath_of_the_wild_be.security.jwt.JwtTokenUtil;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/oauth")
//public class KakaoAuthController {
//
//    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
//    private String kakaoRestApiKey;
//
//    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
//    private String kakaoRedirectUri;
//
//    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
//    private String kakaoClientSecret;
//
//    private final JwtTokenUtil jwtTokenUtil;
//
//    public KakaoAuthController(JwtTokenUtil jwtTokenUtil) {
//        this.jwtTokenUtil = jwtTokenUtil;
//    }
//
//    @GetMapping("/kakao")
//    public void kakaoLogin(HttpServletResponse response) throws IOException {
//        String kakaoUrl = "https://kauth.kakao.com/oauth/authorize?client_id=" + kakaoRestApiKey
//                + "&redirect_uri=" + kakaoRedirectUri
//                + "&response_type=code";
//        response.sendRedirect(kakaoUrl);
//    }
//
//    @GetMapping("/kakao/callback")
//    public void kakaoCallback(@RequestParam String code, HttpServletResponse response) throws IOException {
//        String accessToken = getKakaoAccessToken(code);
//        String email = getUserEmailFromKakao(accessToken);
//
//        // JWT 토큰 생성
//        String jwtToken = jwtTokenUtil.generateToken(email);
//
//        // JWT 토큰을 프론트엔드로 리디렉션하여 전달
//        response.sendRedirect("http://breathofthewildfe.s3-website.ap-northeast-2.amazonaws.com?token=" + jwtToken);
//    }
//
//    private String getKakaoAccessToken(String code) {
//        String tokenUrl = "https://kauth.kakao.com/oauth/token";
//        RestTemplate restTemplate = new RestTemplate();
//
//        Map<String, String> params = new HashMap<>();
//        params.put("grant_type", "authorization_code");
//        params.put("client_id", kakaoRestApiKey);
//        params.put("redirect_uri", kakaoRedirectUri);
//        params.put("code", code);
//        params.put("client_secret", kakaoClientSecret);
//
//        Map<String, Object> response = restTemplate.postForObject(tokenUrl, params, Map.class);
//        return (String) response.get("access_token");
//    }
//
//    private String getUserEmailFromKakao(String accessToken) {
//        String userInfoUrl = "https://kapi.kakao.com/v2/user/me";
//        RestTemplate restTemplate = new RestTemplate();
//
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Authorization", "Bearer " + accessToken);
//
//        Map<String, Object> response = restTemplate.postForObject(userInfoUrl, headers, Map.class);
//        Map<String, Object> kakaoAccount = (Map<String, Object>) response.get("kakao_account");
//
//        return (String) kakaoAccount.get("email");
//    }
//}
