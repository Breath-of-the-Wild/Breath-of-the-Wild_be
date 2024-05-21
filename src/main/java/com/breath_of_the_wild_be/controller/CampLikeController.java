package com.breath_of_the_wild_be.controller;


import com.breath_of_the_wild_be.domain.CampLike;
import com.breath_of_the_wild_be.service.camplikeService.CampLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/camplike")
@Slf4j
public class CampLikeController {

    private final CampLikeService campLikeService;

    @Autowired
    public CampLikeController(CampLikeService campLikeService) {
        this.campLikeService = campLikeService;
    }

    @PostMapping
    public ResponseEntity<CampLike> saveCampLike(@RequestBody CampLike campLike) {
        CampLike savedCampLike = campLikeService.saveCampLike(campLike);
        return ResponseEntity.ok(savedCampLike);
    }

    @GetMapping("/mno/{mno}")
    public ResponseEntity<List<CampLike>> getCampLikesByMno(@PathVariable String memberemail) {
        List<CampLike> campLikes = campLikeService.getCampLikesByMno(memberemail);
        return ResponseEntity.ok(campLikes);
    }

    @GetMapping("/camp/{campId}")
    public ResponseEntity<List<CampLike>> getCampLikesByCampId(@PathVariable Long campId) {
        List<CampLike> campLikes = campLikeService.getCampLikesByCampId(campId);
        return ResponseEntity.ok(campLikes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampLike(@PathVariable Long id) {
        campLikeService.deleteCampLike(id);
        return ResponseEntity.noContent().build();
    }
}