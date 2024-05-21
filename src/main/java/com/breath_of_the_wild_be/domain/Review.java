package com.breath_of_the_wild_be.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "content")
    private String content;

    @Column(name = "image_file")
    private String imageFile;

    @Builder.Default
    @Column(name = "like_cnt")
    private Integer likeCount = 0;

    @Builder.Default
    @Column(name = "rate_cnt")
    private Integer rateCount = 0;

    @Builder.Default
    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "camp_id")
    private Long contentId;

    @Column(name = "email")
    private String mid;
}
