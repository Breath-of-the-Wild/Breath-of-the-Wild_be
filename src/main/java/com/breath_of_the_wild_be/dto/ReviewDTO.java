package com.breath_of_the_wild_be.dto;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor  // 기본 생성자 추가
@AllArgsConstructor
@Data
public class ReviewDTO {

    private Long reviewId;
    private String content;
    private String imageFile;
    private Integer likeCount;
    private Integer rateCount;
    private Date createdDate;
    private Date lastModifiedDate;
    private String createdBy;
    private String lastModifiedBy;
    private Long campId;
    private Long memberId;
}