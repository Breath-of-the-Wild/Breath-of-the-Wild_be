package com.breath_of_the_wild_be.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor  // 기본 생성자 추가
@AllArgsConstructor
public class CampLikeDTO {

    private Long campLikeId;
    private Date createdDate;
    private Date lastModified;
    private Long campId;
    private Long memberId;

}