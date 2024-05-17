package com.breath_of_the_wild_be.controller;

import com.breath_of_the_wild_be.domain.Festival;
import com.breath_of_the_wild_be.service.festivalService.FestivalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/festivals")

public class FestivalController {

    private final FestivalService festivalService;

    @Autowired
    public FestivalController(FestivalService festivalService) {
        this.festivalService = festivalService;
    }

    @PostMapping("/list")
    public List<Festival> getAllFestivals() {
        return festivalService.getAllFestivals();
    }
}
