package com.breath_of_the_wild_be.controller;

import com.breath_of_the_wild_be.domain.Camping;
import com.breath_of_the_wild_be.service.campingService.CampingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{contentId}")
    public ResponseEntity<Camping> getCampingByContentId(@PathVariable Long contentId) {
        Camping camping = campingService.findByContentId(contentId);
        if (camping != null) {
            return ResponseEntity.ok(camping);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/list/{region}")
    public ResponseEntity<List<Camping>> getCampingByRegion(@PathVariable String region) {
        List<Camping> campingList = campingService.getCampingListByRegion(region);
        if (campingList != null && !campingList.isEmpty()) {
            return ResponseEntity.ok(campingList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/test/{id}")
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

    @GetMapping("/search")
    public List<Camping> searchCampings(
            @RequestParam String searchType,
            @RequestParam String searchValue) {
        return campingService.searchCampings(searchType, searchValue);
    }


}