package com.breath_of_the_wild_be.controller;

import com.breath_of_the_wild_be.domain.Festival;
import com.breath_of_the_wild_be.service.festivalService.FestivalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/festivals")

public class FestivalController {

    private final FestivalService festivalService;

    @Autowired
    public FestivalController(FestivalService festivalService) {
        this.festivalService = festivalService;
    }

    @GetMapping("/all")
    public List<Festival> getAllFestivals() {
        return festivalService.getAllFestivals();
    }

    @GetMapping("/{contentid}")
    public ResponseEntity<Festival> getFestivalByContentId(@PathVariable String contentid) {
        Festival festival = festivalService.findByContentId(contentid);
        if (festival != null) {
            return ResponseEntity.ok(festival);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public List<Festival> searchFestival(
            @RequestParam String searchType,
            @RequestParam String searchValue) {
        return festivalService.searchFestival(searchType, searchValue);
    }
}