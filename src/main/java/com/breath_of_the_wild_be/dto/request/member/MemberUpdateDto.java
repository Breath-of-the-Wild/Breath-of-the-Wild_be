package com.breath_of_the_wild_be.dto.request.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * -Request-
 * 회원 정보 변경 요청 dto
 */

@Getter
@Setter
@NoArgsConstructor
public class MemberUpdateDto {

    private String email;
    private String password;
    private String passwordCheck;
    private String username;
    private String birth;

    @Builder
    public MemberUpdateDto(String email, String password, String passwordCheck, String username) {
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.username = username;
    }
}
