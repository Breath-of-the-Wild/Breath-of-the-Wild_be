package com.breath_of_the_wild_be.controller;

import com.breath_of_the_wild_be.domain.Camping;
import com.breath_of_the_wild_be.service.campingService.CampingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/camping")
public class CampingController {

    private final CampingService campingService;

    @Autowired
    public CampingController(CampingService campingService) {
        this.campingService = campingService;
    }

    @GetMapping("/all")
    public List<Camping> getAllCampings() {
        return campingService.getAllCampings();
    }

    @GetMapping("/{id}")
    public Camping getCampingById(@PathVariable Long id) {
        return campingService.getCampingById(id);
    }

    @PostMapping("/add")
    public Camping addCamping(@RequestBody Camping camping) {
        return campingService.addCamping(camping);
    }

    @PutMapping("/update")
    public Camping updateCamping(@RequestBody Camping camping) {
        return campingService.updateCamping(camping);
    }

    @DeleteMapping("/{id}")
    public void deleteCamping(@PathVariable Long id) {
        campingService.deleteCamping(id);
    }


}