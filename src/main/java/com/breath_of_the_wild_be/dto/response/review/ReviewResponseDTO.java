package com.breath_of_the_wild_be.dto.response.review;

import lombok.*;

import java.util.Date;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {
    private Long reviewId;
    private String title;
    private String content;
    private String imageFile;
    private Integer likeCount;
    private Integer rateCount;
    private Date createdDate;
    private String createdBy;
    private Long contentId;
    private String email;
    private String facltNm; // Added field to include the facility name

}