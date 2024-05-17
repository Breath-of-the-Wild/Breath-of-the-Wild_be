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

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "image_file")
    private String imageFile;

    @Column(name = "like_cnt", nullable = false)
    private Integer likeCount;

    @Column(name = "rate_cnt", nullable = false)
    private Integer rateCount;

    @Builder.Default
    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "camp_id", nullable = false)
    private Long campId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;
}
