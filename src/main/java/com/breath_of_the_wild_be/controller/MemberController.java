package com.breath_of_the_wild_be.controller;

import com.breath_of_the_wild_be.dto.request.member.MemberLoginDto;
import com.breath_of_the_wild_be.dto.request.member.MemberRegisterDto;
import com.breath_of_the_wild_be.dto.request.member.MemberUpdateDto;
import com.breath_of_the_wild_be.dto.response.member.MemberResponseDto;
import com.breath_of_the_wild_be.dto.response.member.MemberTokenDto;
import com.breath_of_the_wild_be.domain.Member;
import com.breath_of_the_wild_be.security.jwt.JwtTokenUtil;
import com.breath_of_the_wild_be.service.memberService.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping("/checkId")
    public ResponseEntity<?> checkIdDuplicate(@RequestParam String email) {
        memberService.checkIdDuplicate(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/register")
    public ResponseEntity<MemberResponseDto> register(@RequestBody MemberRegisterDto memberRegisterDTO) {
        MemberResponseDto successMember = memberService.register(memberRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(successMember);
    }

    @PostMapping("/login")
    public ResponseEntity<MemberTokenDto> login(@RequestBody MemberLoginDto memberLoginDTO, HttpServletResponse response) {
        MemberTokenDto loginDTO = memberService.login(memberLoginDTO);

        // 액세스 토큰 쿠키 설정
        Cookie accessTokenCookie = new Cookie("access_token", loginDTO.getToken());
        accessTokenCookie.setHttpOnly(true);
       accessTokenCookie.setSecure(true); // HTTPS에서만 사용
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(jwtTokenUtil.getExpiryDuration(loginDTO.getToken()));

        // 리프레시 토큰 쿠키 설정
        Cookie refreshTokenCookie = new Cookie("refresh_token", loginDTO.getRefreshToken());
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true); // HTTPS에서만 사용
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(jwtTokenUtil.getExpiryDuration(loginDTO.getRefreshToken()));

        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);

        return ResponseEntity.status(HttpStatus.OK).body(loginDTO);
    }

    @PostMapping("/checkPwd")
    public ResponseEntity<MemberResponseDto> check(
            @AuthenticationPrincipal Member member,
            @RequestBody Map<String, String> request) {
        String password = request.get("password");
        MemberResponseDto memberInfo = memberService.check(member, password);
        return ResponseEntity.status(HttpStatus.OK).body(memberInfo);
    }

    @PostMapping("/update")
    public ResponseEntity<MemberResponseDto> update(
            @AuthenticationPrincipal Member member,
            @RequestBody MemberUpdateDto memberUpdateDTO) {
        MemberResponseDto memberUpdate = memberService.update(member, memberUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(memberUpdate);
    }
}
