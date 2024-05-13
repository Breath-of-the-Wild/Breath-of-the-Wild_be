package com.breath_of_the_wild_be.service.memberService;


import com.breath_of_the_wild_be.dto.TodoDTO;
import com.breath_of_the_wild_be.dto.memberDTO.MemberDTO;

import java.util.Optional;

public interface MemberService {

    Long register(MemberDTO memberDTO);

    MemberDTO findById(Long id);

    void update(MemberDTO memberDTO);

    void deleteById(Long id);

    Long registers(MemberDTO memberDTO);



//    String login(String email, String password);
}