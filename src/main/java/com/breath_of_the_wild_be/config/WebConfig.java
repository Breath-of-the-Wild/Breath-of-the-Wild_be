package com.breath_of_the_wild_be.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // 허용할 출처를 설정합니다.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드를 설정합니다.
                .allowedHeaders("*") // 허용할 요청 헤더를 설정합니다.
                .allowCredentials(true) // 자격 증명 허용 여부를 설정합니다.
                .maxAge(3600); // 프리플라이트 요청 결과를 캐싱할 시간을 설정합니다.
    }
}
