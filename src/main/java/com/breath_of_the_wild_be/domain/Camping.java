package com.breath_of_the_wild_be.domain;

import com.breath_of_the_wild_be.service.campingService.JsonToMapConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_camping")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Camping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long campid;

    @Column(name = "content_id", unique = true)
    private Long contentId;

    @Column(name = "facltNm")
    private String facltNm;

    @Column(name = "line_intro", length = 500)
    private String lineIntro;

    @Column(name = "intro", length = 1500)
    private String intro;

    @Column(name = "first_image_url")
    private String firstImageUrl;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "modified_time")
    private LocalDateTime modifiedTime;


    private String allar;


    private String insrncAt;


    private String trsagntNo;


    private String bizrno;

    private String facltDivNm;

    private String mangeDivNm;


    private String mgcDiv;


    private String manageSttus;


    private String hvofBgnde;


    private String hvofEnddle;

    @Column(length = 1000)
    private String featureNm;


    private String induty;


    private String lctCl;


    private String doNm;


    private String sigunguNm;


    private String zipcode;


    private String addr1;


    private String addr2;


    private String mapX;


    private String mapY;

    @Column(length = 1500)
    private String direction;


    private String tel;


    private String homepage;

    @Column(length = 1000)
    private String resveUrl;


    private String resveCl;

    @Transient
    private String manageNmpr;


    private String gnrlSiteCo;

    @Transient
    private String autoSiteCo;

    @Transient
    private String glampSiteCo;

    @Transient
    private String caravSiteCo;

    @Transient
    private String indvdlCaravSiteCo;

    @Transient
    private String sitedStnc;

    @Transient
    private String siteMg1Width;

    @Transient
    private String siteMg2Width;

    @Transient
    private String siteMg3Width;

    @Transient
    private String siteMg1Vrticl;

    @Transient
    private String siteMg2Vrticl;

    @Transient
    private String siteMg3Vrticl;

    @Transient
    private String siteMg1Co;

    @Transient
    private String siteMg2Co;

    @Transient
    private String siteMg3Co;

    @Transient
    private String siteBottomCl1;

    @Transient
    private String siteBottomCl2;

    @Transient
    private String siteBottomCl3;

    @Transient
    private String siteBottomCl4;

    @Transient
    private String siteBottomCl5;

    @Transient
    private String tooltip;


    private String glampInnerFclty;


    private String caravInnerFclty;


    private String prmisnDe;


    private String operPdCl;


    private String operDeCl;


    private String trlerAcmpnyAt;


    private String caravAcmpnyAt;

    @Transient
    private String toiletCo;

    @Transient
    private String swrmCo;

    @Transient
    private String wtrplCo;

    @Transient
    private String brazierCl;


    private String sbrsCl;


    private String sbrsEtc;

    private String posblFcltyCl;

    @Transient
    private String posblFcltyEtc;

    @Transient
    private String clturEventAt;

    @Transient
    private String clturEvent;

    @Transient
    private String exprnProgrmAt;

    @Transient
    private String exprnProgrm;

    @Transient
    private String extshrCo;

    @Transient
    private String frprvtWrppCo;

    @Transient
    private String frprvtSandCo;

    @Transient
    private String fireSensorCo;

    private String themaEnvrnCl;


    private String eqpmnLendCl;


    private String animalCmgCl;

    @Transient
    private String tourEraCl;


    @Convert(converter = JsonToMapConverter.class)
    private Map<String, Object> items;
}