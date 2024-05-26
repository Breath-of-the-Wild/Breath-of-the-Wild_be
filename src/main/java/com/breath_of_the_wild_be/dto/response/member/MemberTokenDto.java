package com.breath_of_the_wild_be.dto.response.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberTokenDto {
    private String email;
    private String username;
    private String token;
    private String refreshToken;

    public static MemberTokenDto fromEntity(UserDetails userDetails, String token, String refreshToken, String username) {
        return new MemberTokenDto(
                userDetails.getUsername(),
                username,
                token,
                refreshToken
        );
    }
}
