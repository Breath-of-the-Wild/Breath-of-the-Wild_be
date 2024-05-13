package com.breath_of_the_wild_be.dto.memberDTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
}