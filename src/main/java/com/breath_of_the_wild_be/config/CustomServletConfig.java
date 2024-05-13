package com.breath_of_the_wild_be.config;

import com.breath_of_the_wild_be.controller.formatter.LocalDateFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//CORS(Cross-Origin Resource Sharing) : Ajax를 이용해서 서비스를 호출하게 되면
// cors 때문에 정상적으로 호출이 제한 됨
//CORS 설정은 @Controller 가 있는 클래스에 @CrossOrigin을 적용하거나
// SpringSecurity 를 이용하여 설정
//  @CrossOrigin 설정은 모든 컨트롤러에 개별적으로 적용해야 함
//  이 예제는 WebMvcConfigurer의 설정으로 사용


@Configuration
public class CustomServletConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addFormatter(new LocalDateFormatter());
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(300)
                .allowedHeaders("Authorization", "Cache-Control", "Content-Type");
    }

}
