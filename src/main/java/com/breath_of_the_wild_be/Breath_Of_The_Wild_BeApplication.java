package com.breath_of_the_wild_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Breath_Of_The_Wild_BeApplication {

    public static void main(String[] args) {
        SpringApplication.run(Breath_Of_The_Wild_BeApplication.class, args);
    }

}
