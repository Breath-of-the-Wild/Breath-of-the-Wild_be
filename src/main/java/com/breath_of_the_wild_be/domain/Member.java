package com.breath_of_the_wild_be.domain;

import com.breath_of_the_wild_be.common.BaseTimeEntity;
import com.breath_of_the_wild_be.common.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name="tbl_member")
public class Member extends BaseTimeEntity implements UserDetails {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long memberid;

    // 이메일로 로그인함
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;


    private String username;


    private String birth;

    @Enumerated(EnumType.STRING)
    private Role roles;


    //== 생성자 Builder ==//
    @Builder
    public Member(String email, String password, String username, Role roles, String birth) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.roles = roles;
        this.birth = birth;
    }

    //== update ==//
    public void update(String password, String username) {
        this.password = password;
        this.username = username;
    }

    //========== UserDetails implements ==========//
    /**
     * Token을 고유한 Email 값으로 생성합니다
     * @return email;
     */
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add( new SimpleGrantedAuthority("ROLE_" + this.roles.name()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
