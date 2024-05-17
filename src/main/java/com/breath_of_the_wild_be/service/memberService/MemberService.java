package com.breath_of_the_wild_be.service.memberService;

import com.breath_of_the_wild_be.dto.request.member.MemberLoginDto;
import com.breath_of_the_wild_be.dto.request.member.MemberRegisterDto;
import com.breath_of_the_wild_be.dto.request.member.MemberUpdateDto;
import com.breath_of_the_wild_be.dto.response.member.MemberResponseDto;
import com.breath_of_the_wild_be.dto.response.member.MemberTokenDto;
import com.breath_of_the_wild_be.domain.Member;
import org.springframework.http.HttpStatus;

public interface MemberService {
    HttpStatus checkIdDuplicate(String email);
    MemberResponseDto register(MemberRegisterDto registerDto);
    MemberTokenDto login(MemberLoginDto loginDto);
    MemberResponseDto check(Member member, String password);
    MemberResponseDto update(Member member, MemberUpdateDto updateDto);
    void adminregister();
}