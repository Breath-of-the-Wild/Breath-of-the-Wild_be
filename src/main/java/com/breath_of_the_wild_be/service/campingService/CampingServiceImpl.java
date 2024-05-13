package com.breath_of_the_wild_be.service.campingService;

import com.breath_of_the_wild_be.domain.Camping;
import com.breath_of_the_wild_be.dto.CampingDTO;
import com.breath_of_the_wild_be.repository.campingRepository.CampingRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CampingServiceImpl implements CampingService {
    @PersistenceContext

    private EntityManager entityManager;

    private final RestTemplate restTemplate;

    private final CampingRepository campingRepository;

    @Autowired
    public CampingServiceImpl(CampingRepository campingRepository, RestTemplateBuilder restTemplateBuilder) {
        this.campingRepository = campingRepository;
        this.restTemplate = restTemplateBuilder
                .additionalMessageConverters(new MappingJackson2HttpMessageConverter(), new MappingJackson2XmlHttpMessageConverter())
                .build();
    }

    @Override
    public List<Camping> getAllCampings() {
        return campingRepository.findAll();
    }

    @Override
    public Camping getCampingById(Long id) {
        Optional<Camping> campingOptional = campingRepository.findById(id);
        return campingOptional.orElse(null);
    }

    @Override
    public Camping addCamping(Camping camping) {
        return campingRepository.save(camping);
    }

    @Override
    public Camping updateCamping(Camping camping) {
        return campingRepository.save(camping);
    }

    @Override
    public void deleteCamping(Long id) {
        campingRepository.deleteById(id);
    }






//    private List<CampingDTO> fetchDataFromAPI(int pageNo, int pageSize) {
//        // API 호출 및 데이터 가져오기
//        String apiUrl = "https://apis.data.go.kr/B551011/GoCamping/basedList?numOfRows=" + pageSize + "&pageNo=" + pageNo + "&MobileOS=WIN&MobileApp=TEST&serviceKey=THdy3Wa2gPc%2FhA6UmjpvfZ087NbuDZ2NfuvNd%2FgLStW1jA%2BViMfbfdYIdyX8upEoV16D9YRMZk7SEnU5FQcY7Q%3D%3D&_type=json";
//
//        // API 호출하여 JSON 형식의 응답을 CampingDTO 리스트로 받아옴
//        ResponseEntity<CampingDTO[]> responseEntity = restTemplate.getForEntity(apiUrl, CampingDTO[].class);
//        CampingDTO[] campingDTOs = responseEntity.getBody();
//
//        // CampingDTO 배열을 리스트로 변환하여 반환
//        return campingDTOs != null ? Arrays.asList(campingDTOs) : new ArrayList<>();
//    }
//
//    @PostConstruct
//    @Transactional
//    public void saveCampingDataFromAPI() {
//        // 총 데이터 수와 한 페이지당 표시할 데이터 수
//        int totalDataCount = 4000;
//        int pageSize = 100;
//
//        // 페이지 수 계산
//        int pageCount = (int) Math.ceil((double) totalDataCount / pageSize);
//
//        // 모든 페이지에 대한 요청 및 데이터 저장
//        for (int page = 1; page <= pageCount; page++) {
//            List<CampingDTO> campingDTOList = fetchDataFromAPI(page, pageSize);
//
//            // 가져온 데이터를 DB에 저장하기
//            for (CampingDTO campingDTO : campingDTOList) {
//                Camping camping = mapToCampingEntity(campingDTO);
//                entityManager.persist(camping);
//            }
//        }
//    }
//
//    private Camping mapToCampingEntity(CampingDTO campingDTO) {
//        return Camping.builder()
//                .contentId(campingDTO.getContentId())
//                .facltNm(campingDTO.getFacltNm())
//                .lineIntro(campingDTO.getLineIntro())
//                .intro(campingDTO.getIntro())
//                .allar(campingDTO.getAllar())
//                .insrncAt(campingDTO.getInsrncAt())
//                .trsagntNo(campingDTO.getTrsagntNo())
//                .bizrno(campingDTO.getBizrno())
//                .facltDivNm(campingDTO.getFacltDivNm())
//                .mangeDivNm(campingDTO.getMangeDivNm())
//                .mgcDiv(campingDTO.getMgcDiv())
//                .manageSttus(campingDTO.getManageSttus())
//                .hvofBgnde(campingDTO.getHvofBgnde())
//                .hvofEnddle(campingDTO.getHvofEnddle())
//                .featureNm(campingDTO.getFeatureNm())
//                .induty(campingDTO.getInduty())
//                .lctCl(campingDTO.getLctCl())
//                .doNm(campingDTO.getDoNm())
//                .sigunguNm(campingDTO.getSigunguNm())
//                .zipcode(campingDTO.getZipcode())
//                .addr1(campingDTO.getAddr1())
//                .addr2(campingDTO.getAddr2())
//                .mapX(campingDTO.getMapX())
//                .mapY(campingDTO.getMapY())
//                .direction(campingDTO.getDirection())
//                .tel(campingDTO.getTel())
//                .homepage(campingDTO.getHomepage())
//                .resveUrl(campingDTO.getResveUrl())
//                .resveCl(campingDTO.getResveCl())
//                .manageNmpr(campingDTO.getManageNmpr())
//                .gnrlSiteCo(campingDTO.getGnrlSiteCo())
//                .autoSiteCo(campingDTO.getAutoSiteCo())
//                .glampSiteCo(campingDTO.getGlampSiteCo())
//                .caravSiteCo(campingDTO.getCaravSiteCo())
//                .indvdlCaravSiteCo(campingDTO.getIndvdlCaravSiteCo())
//                .sitedStnc(campingDTO.getSitedStnc())
//                .siteMg1Width(campingDTO.getSiteMg1Width())
//                .siteMg2Width(campingDTO.getSiteMg2Width())
//                .siteMg3Width(campingDTO.getSiteMg3Width())
//                .siteMg1Vrticl(campingDTO.getSiteMg1Vrticl())
//                .siteMg2Vrticl(campingDTO.getSiteMg2Vrticl())
//                .siteMg3Vrticl(campingDTO.getSiteMg3Vrticl())
//                .siteMg1Co(campingDTO.getSiteMg1Co())
//                .siteMg2Co(campingDTO.getSiteMg2Co())
//                .siteMg3Co(campingDTO.getSiteMg3Co())
//                .siteBottomCl1(campingDTO.getSiteBottomCl1())
//                .siteBottomCl2(campingDTO.getSiteBottomCl2())
//                .siteBottomCl3(campingDTO.getSiteBottomCl3())
//                .siteBottomCl4(campingDTO.getSiteBottomCl4())
//                .siteBottomCl5(campingDTO.getSiteBottomCl5())
//                .tooltip(campingDTO.getTooltip())
//                .glampInnerFclty(campingDTO.getGlampInnerFclty())
//                .caravInnerFclty(campingDTO.getCaravInnerFclty())
//                .prmisnDe(campingDTO.getPrmisnDe())
//                .operPdCl(campingDTO.getOperPdCl())
//                .operDeCl(campingDTO.getOperDeCl())
//                .trlerAcmpnyAt(campingDTO.getTrlerAcmpnyAt())
//                .caravAcmpnyAt(campingDTO.getCaravAcmpnyAt())
//                .toiletCo(campingDTO.getToiletCo())
//                .swrmCo(campingDTO.getSwrmCo())
//                .wtrplCo(campingDTO.getWtrplCo())
//                .brazierCl(campingDTO.getBrazierCl())
//                .sbrsCl(campingDTO.getSbrsCl())
//                .sbrsEtc(campingDTO.getSbrsEtc())
//                .posblFcltyCl(campingDTO.getPosblFcltyCl())
//                .posblFcltyEtc(campingDTO.getPosblFcltyEtc())
//                .clturEventAt(campingDTO.getClturEventAt())
//                .clturEvent(campingDTO.getClturEvent())
//                .exprnProgrmAt(campingDTO.getExprnProgrmAt())
//                .exprnProgrm(campingDTO.getExprnProgrm())
//                .extshrCo(campingDTO.getExtshrCo())
//                .frprvtWrppCo(campingDTO.getFrprvtWrppCo())
//                .frprvtSandCo(campingDTO.getFrprvtSandCo())
//                .fireSensorCo(campingDTO.getFireSensorCo())
//                .themaEnvrnCl(campingDTO.getThemaEnvrnCl())
//                .eqpmnLendCl(campingDTO.getEqpmnLendCl())
//                .animalCmgCl(campingDTO.getAnimalCmgCl())
//                .tourEraCl(campingDTO.getTourEraCl())
//                .firstImageUrl(campingDTO.getFirstImageUrl())
//                .createdtime(campingDTO.getCreatedtime())
//                .modifiedtime(campingDTO.getModifiedtime())
//                .build();
//    }
//
//
//    private ClientHttpRequestFactory clientHttpRequestFactory(int timeout) {
//        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
//        factory.setConnectTimeout(timeout);
//        factory.setReadTimeout(timeout);
//        return factory;
//    }
//
//    @PostConstruct
//    public void initializeData() {
//        fetchDataAndSaveToDatabase();
//    }


}

