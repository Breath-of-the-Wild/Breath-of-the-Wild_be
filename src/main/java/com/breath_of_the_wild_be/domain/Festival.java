package com.breath_of_the_wild_be.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_festival")
public class Festival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "festival_id")
    private Long festivalId;

    @Column(name = "addr1")
    private String addr1;

    @Column(name = "addr2")
    private String addr2;

    @Column(name = "booktour")
    private String bookTour;

    @Column(name = "cat1")
    private String category1;

    @Column(name = "cat2")
    private String category2;

    @Column(name = "cat3")
    private String category3;

    @Column(name = "contentid")
    private String contentId;

    @Column(name = "contenttypeid")
    private String contentTypeId;

    @Column(name = "createdtime")
    private String createdTime;

    @Column(name = "eventstartdate")
    private String eventStartDate;

    @Column(name = "eventenddate")
    private String eventEndDate;

    @Column(name = "firstimage")
    private String firstImage;

    @Column(name = "firstimage2")
    private String firstImage2;

    @Column(name = "cpyrhtDivCd")
    private String copyrightDivisionCode;

    @Column(name = "mapx")
    private String mapX;

    @Column(name = "mapy")
    private String mapY;

    @Column(name = "mlevel")
    private String mLevel;

    @Column(name = "modifiedtime")
    private String modifiedTime;

    @Column(name = "areacode")
    private String areaCode;

    @Column(name = "sigungucode")
    private String sigunguCode;

    @Column(name = "tel")
    private String tel;

    @Column(name = "title")
    private String title;


}
