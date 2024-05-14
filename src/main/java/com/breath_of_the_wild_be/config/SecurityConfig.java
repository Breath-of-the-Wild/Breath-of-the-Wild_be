package com.breath_of_the_wild_be.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final JwtExceptionFilter jwtExceptionFilter;

  private static final String[] WHITE_LIST = {
    "/auth/**",
  };

  private static final String[] AUTHORIZATED_LIST = {

  };

  private static final String[] USER_LIST = {
    "/account/**",
  };

  private static final String[] ADMIN_LIST = {

  };

  @Bean
  protected SecurityFilterChain config(HttpSecurity http) throws Exception {
    http
      .httpBasic().disable()
      .csrf().disable()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .exceptionHandling()
      .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
      .accessDeniedHandler(new CustomAccessDeniedHandler())
      .and()
      .authorizeHttpRequests(authorize -> authorize
        .requestMatchers(WHITE_LIST).permitAll()
        .requestMatchers(USER_LIST).hasAuthority("USER")
      .and()
      .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
      .addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class));

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
