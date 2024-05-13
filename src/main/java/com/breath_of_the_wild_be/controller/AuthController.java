package com.breath_of_the_wild_be.controller;

import com.breath_of_the_wild_be.dto.memberDTO.LoginRequest;
import com.breath_of_the_wild_be.dto.memberDTO.MemberDTO;
import com.breath_of_the_wild_be.service.memberService.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final MemberService memberService;

    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }

//    @PostMapping("/login")
//    public ResponseEntity<MemberDTO> login(@RequestBody LoginRequest loginRequest) {
//        Optional<MemberDTO> memberOptional = memberService.login(loginRequest.getEmail(), loginRequest.getPw());
//        return memberOptional
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
//    }
}