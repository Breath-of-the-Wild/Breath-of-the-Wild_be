package com.breath_of_the_wild_be.timer;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimerService {

    @Scheduled(fixedDelay = 2000)
    public void run() {
        System.out.println("TimerSErvice run");
    }
}
