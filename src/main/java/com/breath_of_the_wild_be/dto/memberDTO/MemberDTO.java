package com.breath_of_the_wild_be.dto.memberDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor  // 기본 생성자 추가
@AllArgsConstructor
public class MemberDTO {
    private Long mno;
    private String email;
    private String pw;
    private String username;
    private String phone;
    private String birth;
    private LocalDate regdate;
    private Integer f_count;
    private Integer flock;

}
