package com.breath_of_the_wild_be.config;

import com.breath_of_the_wild_be.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class JwtExceptionFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (JwtException e) {
      log.info("JwtExceptionFilter : " + e.getMessage());
      response.setContentType("application/json;charset=UTF-8");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            // write denied msg
      response.getWriter()
        .println(ResponseUtil.getErrorResponseJson(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage()));
    }
  }

  public void setErrorResponse(HttpStatus status, HttpServletResponse response, Throwable e) throws IOException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> map = new HashMap<>();
    map.put("timestamp", LocalDateTime.now().toString());
    map.put("message", e.getMessage());
    response.getWriter().write(mapper.writeValueAsString(map));
  }
}
