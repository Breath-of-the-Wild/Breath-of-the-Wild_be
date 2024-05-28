package com.breath_of_the_wild_be.dto.request;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampLikeRequestDTO {

    private Long contentId;
    private String email;
    private String email2;
}
