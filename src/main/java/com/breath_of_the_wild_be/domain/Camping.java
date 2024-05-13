package com.breath_of_the_wild_be.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_camping")
public class Camping {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content_id")
    private Long contentId;

    @Column(name = "facilities_name")
    private String facilitiesName;

    @Column(name = "line_intro", length = 500)
    private String lineIntro;

    @Column(name = "intro", length = 1000)
    private String intro;

    private String firstImageUrl;
    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "modified_time")
    private LocalDateTime modifiedTime;


}