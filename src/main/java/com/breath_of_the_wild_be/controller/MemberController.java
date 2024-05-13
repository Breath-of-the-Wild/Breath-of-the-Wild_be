package com.breath_of_the_wild_be.controller;


import com.breath_of_the_wild_be.dto.TodoDTO;
import com.breath_of_the_wild_be.dto.memberDTO.LoginRequest;
import com.breath_of_the_wild_be.dto.memberDTO.MemberDTO;
import com.breath_of_the_wild_be.service.memberService.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody MemberDTO memberDTO) {
        Long memberId = memberService.register(memberDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> findById(@PathVariable Long id) {
        MemberDTO memberDTO = memberService.findById(id);
        return ResponseEntity.ok(memberDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody MemberDTO memberDTO) {
        memberDTO.setMno(id);
        memberService.update(memberDTO);
        return ResponseEntity.ok("Member updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        memberService.deleteById(id);
        return ResponseEntity.ok("Member deleted successfully");
    }

    @PostMapping("/registers")
    public Map<String, Long> registers(@RequestBody MemberDTO memberDTO) {
        log.info("******  memberDTO:  ******   " + memberDTO);

        Long mno = memberService.registers(memberDTO);

        return Map.of("MNO", mno);
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//        String token = memberService.login(loginRequest.getEmail(), loginRequest.getPassword());
//
//        if (token != null) {
//            return ResponseEntity.ok(token);
//        } else {
//            return ResponseEntity.badRequest().body("Login failed");
//        }
//    }
}