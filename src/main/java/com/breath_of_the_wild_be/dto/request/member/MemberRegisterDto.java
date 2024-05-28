package com.breath_of_the_wild_be.dto.request.member;

import com.breath_of_the_wild_be.common.Role;
import com.breath_of_the_wild_be.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * -Request-
 * 회원 가입 요청 dto
 */
@Getter
@Setter
@NoArgsConstructor
public class MemberRegisterDto {

    private String email;
    private String password;
    private String passwordCheck;
    private String username;
    private String birth;

    @Builder
    public MemberRegisterDto(String email, String password, String passwordCheck, String username) {
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.username = username;
        this.birth = birth;
    }

    // DTO -> Entity
    public static Member ofEntity(MemberRegisterDto dto) {
        return Member.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .roles(Role.USER)
                .birth(dto.getBirth())
                .build();
    }
}
