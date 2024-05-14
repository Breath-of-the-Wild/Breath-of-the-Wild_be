package com.breath_of_the_wild_be.config;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer customizeObjectMapper() {
    return builder -> {
      // Customize Jackson object mapper if needed
      // For example, configure date format
      builder.simpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    };
  }
}
