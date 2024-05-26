package com.breath_of_the_wild_be.repository;


import com.breath_of_the_wild_be.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUsername(String username);
    void deleteByToken(String token);
    void deleteByUsername(String username);
}
