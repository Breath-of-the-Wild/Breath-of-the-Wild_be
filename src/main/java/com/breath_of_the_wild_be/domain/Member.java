package com.breath_of_the_wild_be.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Table(name = "tbl_member")
    public class Member {

        @Id
        @GeneratedValue
        private Long mno;
        @Column(unique = true)
        private String email;
        private String pw;
        private String username;
        private String phone;
        private String birth;
        @Builder.Default // 기본값 설정을 위한 lombok annotation
        private Timestamp regdate = Timestamp.valueOf(LocalDateTime.now()); // 현재 시간으로 설정

        private Integer f_count;
        private Integer flock;




}
