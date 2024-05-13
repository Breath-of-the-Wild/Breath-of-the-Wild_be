package com.breath_of_the_wild_be.repository.memberRepository;

import com.breath_of_the_wild_be.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmailAndPw(String email, String pw);
    Optional<Member> findByEmail(String email);

}