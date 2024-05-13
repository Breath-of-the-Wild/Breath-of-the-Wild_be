package com.breath_of_the_wild_be.controller.util;


import com.breath_of_the_wild_be.domain.Member;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.sql.Timestamp;


public class Initializer {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        // 관리자 계정 생성
        Member admin = new Member();
        admin.setEmail("admin");
        admin.setPw("admin123");
        admin.setUsername("관리자");
        admin.setPhone("010-0000-0000");
        admin.setBirth("1992-07-15");
        admin.setRegdate(new Timestamp(System.currentTimeMillis()));
        admin.setF_count(0);
        admin.setFlock(0);

        entityManager.persist(admin);
    }


}