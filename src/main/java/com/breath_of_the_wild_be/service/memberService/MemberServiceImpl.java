package com.breath_of_the_wild_be.service.memberService;


import com.breath_of_the_wild_be.domain.Member;

import com.breath_of_the_wild_be.dto.memberDTO.MemberDTO;
import com.breath_of_the_wild_be.repository.memberRepository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public MemberServiceImpl(MemberRepository memberRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long register(MemberDTO
                                     memberDTO) {
        Member member = modelMapper.map(memberDTO, Member.class);
        Member savedMember = memberRepository.save(member);
        return savedMember.getMno();
    }



    @Override
    public void update(MemberDTO memberDTO) {
        Member member = modelMapper.map(memberDTO, Member.class);
        memberRepository.save(member);
    }

    @Override
    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public MemberDTO findById(Long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        Member member = memberOptional.orElseThrow(() -> new RuntimeException("Member not found"));
        return modelMapper.map(member, MemberDTO.class);
    }

    @Override
    public Long registers(MemberDTO memberDTO) {

        log.info("등록!!! .........");

        Member member = modelMapper.map(memberDTO, Member.class);

        Member savedMember = memberRepository.save(member);

        return savedMember.getMno();

    }


//    @Override
//    public String login(String email, String password) {
//        Optional<Member> memberOptional = memberRepository.findByEmail(email);
//        if (memberOptional.isPresent() && PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(password, memberOptional.get().getPw())) {
//            // JWT 토큰 생성 로직
//            return "token"; // 실제로는 JWT 토큰을 생성하여 반환해야 합니다.
//        }
//        return null; // 로그인 실패
//    }


}