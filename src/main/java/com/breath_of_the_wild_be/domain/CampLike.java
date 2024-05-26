package com.breath_of_the_wild_be.domain;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_camp_like")
public class CampLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id", nullable = false)
    private Camping camping;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email", nullable = false)
    private Member member;

    @Column(name = "liked_time", nullable = false)
    private LocalDateTime likedTime;

    @PrePersist
    protected void onCreate() {
        likedTime = LocalDateTime.now();
    }
}
