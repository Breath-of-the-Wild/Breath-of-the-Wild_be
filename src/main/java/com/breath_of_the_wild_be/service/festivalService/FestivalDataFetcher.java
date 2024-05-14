package com.breath_of_the_wild_be.service.festivalService;

import org.springframework.stereotype.Component;

@Component
public class FestivalDataFetcher {

//    @Autowired
//    private FestivalService festivalService;
//
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//    private static final Logger logger = LoggerFactory.getLogger(FestivalDataFetcher.class);
//
//    @PostConstruct
//    public void fetchDataAndProcess() {
//        for (int attempt = 1; attempt <= 5; attempt++) {
//            try {
//                // 페이지 번호가 1부터 5까지 반복
//                for (int pageNo = 1; pageNo <= 5; pageNo++) {
//                    // API에서 데이터를 가져와서 Festival 객체 리스트로 변환
//                    String apiUrl = "https://apis.data.go.kr/B551011/KorService1/searchFestival1?numOfRows=100&pageNo=" + pageNo + "&MobileOS=win&MobileApp=win&_type=json&arrange=Q&eventStartDate=20240401&serviceKey=tkpuYMyOJPiESQhzLecE1EshwjeUNeXfOJY7y8Rku7L2kh5E%2FbSH7NC7CZ1vvthRi72%2FidxEOUL%2FULnq0WWkHw%3D%3D";
//                    RestTemplate restTemplate = new RestTemplate();
//                    JsonNode response = restTemplate.getForObject(apiUrl, JsonNode.class);
//
//                    if (festivalService != null) {
//                        festivalService.saveFestivals(response.path("response").path("body").path("items"));
//                    } else {
//                        logger.error("FestivalService is not initialized properly.");
//                    }
//                }
//                // 반복이 완료되었으므로 성공한 경우 반복문 종료
//                break;
//            } catch (Exception e) {
//                // 에러 발생 시 에러 로그 출력
//                logger.error("An error occurred while fetching and processing festival data for attempt {}: {}", attempt, e.getMessage());
//                // 여기에 필요한 경우 추가적인 예외 처리 코드를 추가할 수 있습니다.
//            }
//        }
//    }
}