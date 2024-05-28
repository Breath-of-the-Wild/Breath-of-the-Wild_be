package com.breath_of_the_wild_be.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;

    @Column(name = "imagefile")
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

    @Column(name = "created_by")
    private String createdBy;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Camping camping;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private Member member;

}
