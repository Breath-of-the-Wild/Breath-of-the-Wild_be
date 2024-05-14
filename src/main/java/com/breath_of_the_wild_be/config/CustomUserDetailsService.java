package com.breath_of_the_wild_be.config;

import com.breath_of_the_wild_be.domain.account.AccountEntity;
import com.breath_of_the_wild_be.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final AccountRepository accountRepository;

  @Override
  public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
    AccountEntity account = accountRepository.findAccountEntityById(id).orElseThrow(
      () -> new UsernameNotFoundException("User not found with this id :" + id)
    );

    return CustomUserDetails.create(account);
  }

  public UserDetails loadUserByIdx(Long idx) {
    AccountEntity entity = accountRepository.findAccountEntityByIdx(idx).orElseThrow(
      () -> new UsernameNotFoundException("User not found with this idx :" + idx)
    );

    return CustomUserDetails.create(entity);
  }
}
