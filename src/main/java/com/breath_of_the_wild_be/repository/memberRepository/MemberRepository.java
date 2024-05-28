package com.breath_of_the_wild_be.repository.memberRepository;

import com.breath_of_the_wild_be.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByUsername(String email);


    @Query("SELECT m.username FROM Member m WHERE m.email = :email")
    String findUsernameByEmail(@Param("email") String email);
}
