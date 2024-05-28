package com.breath_of_the_wild_be;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EnableBatchProcessing
public class Breath_Of_The_Wild_BeApplication {

    public static void main(String[] args) {
        SpringApplication.run(Breath_Of_The_Wild_BeApplication.class, args);
    }

}
