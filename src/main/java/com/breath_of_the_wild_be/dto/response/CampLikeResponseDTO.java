package com.breath_of_the_wild_be.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor  // 기본 생성자 추가
@AllArgsConstructor
@Data
public class CampLikeResponseDTO {
    private Long likeid;
    private Long contentId;
    private String email;
    private LocalDateTime likedTime;
    private String firstImageUrl; // 추가된 필드
    private String facltNm;       // 추가된 필드
    private String induty;        // 추가된 필드
    private String lctCl;         // 추가된 필드
    private String addr1;         // 추가된 필드
}