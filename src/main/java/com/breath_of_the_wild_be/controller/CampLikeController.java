package com.breath_of_the_wild_be.controller;

import com.breath_of_the_wild_be.dto.response.CampLikeResponseDTO;
import com.breath_of_the_wild_be.dto.request.CampLikeRequestDTO;
import com.breath_of_the_wild_be.service.camplikeService.CampLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/camplikes")
@RequiredArgsConstructor
public class CampLikeController {

    private final CampLikeService campLikeService;

    @PostMapping("/like")
    public ResponseEntity<Void> likeCamping(@RequestBody CampLikeRequestDTO requestDto) {
        campLikeService.likeCamping(requestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/unlike")
    public ResponseEntity<Void> unlikeCamping(@RequestBody CampLikeRequestDTO requestDto) {
        campLikeService.unlikeCamping(requestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<List<CampLikeResponseDTO>> getLikedCampsByUser(@PathVariable String email) {
        List<CampLikeResponseDTO> likedCamps = campLikeService.getLikedCampsByUser(email);
        return ResponseEntity.ok(likedCamps);
    }

    @PostMapping("/liked")
    public ResponseEntity<Map<String, Boolean>> checkIfLiked(@RequestBody CampLikeRequestDTO requestDto) {
        boolean isLiked = campLikeService.checkIfLiked(requestDto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("liked", isLiked);
        return ResponseEntity.ok(response);
    }
}
