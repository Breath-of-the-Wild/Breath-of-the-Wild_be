package com.breath_of_the_wild_be.dto.oauth2;

import com.breath_of_the_wild_be.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User, UserDetails {

    private final Member member;

    public CustomOAuth2User(Member member) {
        this.member = member;
    }

    @Override
    public Map<String, Object> getAttributes() {
        // 적절한 속성을 반환하도록 구현
        return Collections.emptyMap();
    }

    @Override
    public String getName() {
        return member.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return member.getAuthorities();
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return member.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return member.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return member.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return member.isEnabled();
    }
}
