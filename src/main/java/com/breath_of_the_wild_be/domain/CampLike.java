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
@Table(name = "tbl_camplike")
public class CampLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "camp_like_id")
    private Long campLikeId;

    @Builder.Default
    @Column(name = "created_date", nullable = false)
    private Date createdDate = new Date();

    @Column(name = "last_modified")
    private Date lastModified;

    @Column(name = "camp_id", nullable = false)
    private Long campId;

    @Column(name = "mno", nullable = false)
    private Long mno;

    // getters and setters
}
