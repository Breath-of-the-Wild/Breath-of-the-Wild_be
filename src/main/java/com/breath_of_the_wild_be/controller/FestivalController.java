package com.breath_of_the_wild_be.controller;

import com.breath_of_the_wild_be.service.festivalService.FestivalService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/festivals")
public class FestivalController {

    private final FestivalService festivalService;

    @Autowired
    public FestivalController(FestivalService festivalService) {
        this.festivalService = festivalService;
    }

    @GetMapping("/fetch-and-save")
    public ResponseEntity<String> fetchAndSaveFestivals() {
        try {
            // API 호출하여 JSON 데이터 가져오기
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(fetchDataFromAPI());

            // JSON 데이터를 엔티티로 변환하여 DB에 저장
            festivalService.saveFestivals(jsonNode);

            return ResponseEntity.ok("Festivals fetched and saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch and save festivals.");
        }
    }

    // API 호출하여 JSON 데이터 가져오기
    private String fetchDataFromAPI() {
        // 여기에 API 호출 및 JSON 데이터 가져오는 코드 작성
        return ""; // API에서 받아온 JSON 데이터를 문자열로 반환
    }
}
