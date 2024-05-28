package com.breath_of_the_wild_be.dto.request.review;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ReviewRequestDTO {
    private String title;
    private String content;
    private String imageFile; // 이미지 파일을 전달하기 위한 필드
    private String createdBy;
    private Long contentId;
    private String email;
}