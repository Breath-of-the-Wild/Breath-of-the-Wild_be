package com.breath_of_the_wild_be.service.campingService;

import com.breath_of_the_wild_be.dto.CampingDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class CampingDataFetcher {
    private static final Logger logger = LoggerFactory.getLogger(CampingDataFetcher.class);

    private static final String API_URL = "https://apis.data.go.kr/B551011/GoCamping/basedList";
    private static final String SERVICE_KEY = "THdy3Wa2gPc%2FhA6UmjpvfZ087NbuDZ2NfuvNd%2FgLStW1jA%2BViMfbfdYIdyX8upEoV16D9YRMZk7SEnU5FQcY7Q%3D%3D";
    private static final String MOBILE_OS = "WIN";
    private static final String MOBILE_APP = "TEST";
    private static final String TYPE = "json";

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void fetchDataAndProcess() {
        int pageNo = 1; // 페이지 번호
        int numOfRows = 100; // 한 페이지당 표시할 데이터 수

        boolean success = false;

        while (!success) {
            String apiUrl = String.format("%s?numOfRows=%d&pageNo=%d&MobileOS=%s&MobileApp=%s&serviceKey=%s&_type=%s",
                    API_URL, numOfRows, pageNo, MOBILE_OS, MOBILE_APP, SERVICE_KEY, TYPE);

            try {
                ResponseEntity<String> response = new RestTemplate().getForEntity(apiUrl, String.class);

                if (response.getStatusCode().is2xxSuccessful()) {
                    CampingDTO[] campingDTOs = objectMapper.readValue(response.getBody(), CampingDTO[].class);

                    // 가져온 데이터를 처리하는 코드 추가

                    // 다음 페이지로 이동
                    pageNo++;

                    // 만약 가져올 데이터가 더 이상 없으면 종료
                    if (campingDTOs == null || campingDTOs.length == 0) {
                        success = true;
                    }
                } else {
                    logger.error("Failed to fetch data. Status code: {}", response.getStatusCodeValue());
                    break;
                }
            } catch (JsonProcessingException e) {
                logger.error("Failed to parse JSON response: {}", e.getMessage());
                break;
            } catch (Exception e) {
                logger.error("An unexpected error occurred: {}", e.getMessage());
                break;
            }
        }
    }
}
