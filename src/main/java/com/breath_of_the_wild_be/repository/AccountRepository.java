package com.breath_of_the_wild_be.repository;

import com.breath_of_the_wild_be.domain.account.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

  Optional<AccountEntity> findAccountEntityById(String id);

  Optional<AccountEntity> findAccountEntityByIdx(Long idx);
}
