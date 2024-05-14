package com.breath_of_the_wild_be.config;

import com.breath_of_the_wild_be.domain.account.AccountEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
public class CustomUserDetails implements UserDetails {

  private Long idx;

  private String id;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public CustomUserDetails(Long idx, String id, String password, Collection<? extends GrantedAuthority> authorities) {
    this.idx = idx;
    this.id = id;
    this.password = password;

    if (authorities == null) {
      this.authorities = null;
    } else {
      this.authorities = new ArrayList<>(authorities);
    }
  }

  public static CustomUserDetails create(AccountEntity account) {
    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(account.getRole()));
    return new CustomUserDetails(account.getIdx(), account.getId(), account.getPassword(), authorities);
  }

  public Long getIdx() {
    return idx;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return id;
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
