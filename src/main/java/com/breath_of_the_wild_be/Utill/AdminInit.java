//package com.breath_of_the_wild_be.Utill;
//
//import com.breath_of_the_wild_be.service.memberService.MemberService;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AdminInit {
//
//    private final MemberService memberService;
//
//    @Autowired
//    public AdminInit(MemberService memberService) {
//        this.memberService = memberService;
//    }
//
//    @PostConstruct
//    public void initializeData() {
//        memberService.adminregister();
//    }
//}