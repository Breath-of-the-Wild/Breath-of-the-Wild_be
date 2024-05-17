package com.breath_of_the_wild_be.service.campingService;

import com.breath_of_the_wild_be.domain.Camping;
import com.breath_of_the_wild_be.repository.campingRepository.CampingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CampingServiceImpl implements CampingService {

    private final CampingRepository campingRepository;

    @Autowired
    public CampingServiceImpl(CampingRepository campingRepository) {
        this.campingRepository = campingRepository;
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




//    public void fetchDataAndSaveToDatabase() {
//        int numOfRows = 100;
//        String mobileOS = "WIN";
//        String mobileApp = "BreathoftheWile";
//        String serviceKey = "THdy3Wa2gPc%2FhA6UmjpvfZ087NbuDZ2NfuvNd%2FgLStW1jA%2BViMfbfdYIdyX8upEoV16D9YRMZk7SEnU5FQcY7Q%3D%3D";
//        String format = "json";
//
//        RestTemplate restTemplate = new RestTemplate();
//        try {
//            for (int i = 1; i <= 100; i++) { // pageNo를 1부터 100까지 반복
//                String apiUrl = String.format("https://apis.data.go.kr/B551011/GoCamping/basedList?numOfRows=%d&pageNo=%d&MobileOS=%s&MobileApp=%s&serviceKey=%s&_type=%s",
//                        numOfRows, i, mobileOS, mobileApp, serviceKey, format);
//
//                // API 응답을 가져옵니다.
//                ResponseEntity<CampingResponse> responseEntity = restTemplate.getForEntity(apiUrl, CampingResponse.class);
//
//                // 응답 코드 확인
//                if (responseEntity.getStatusCode().is2xxSuccessful()) {
//                    CampingResponse response = responseEntity.getBody();
//                    if (response != null && response.getResponse() != null && response.getResponse().getBody() != null &&
//                            response.getResponse().getBody().getItems() != null && response.getResponse().getBody().getItems().getItem() != null) {
//                        // CampingResponse에서 직접 Camping으로 변환하여 저장합니다.
//                        List<Camping> campings = Arrays.asList(response.getResponse().getBody().getItems().getItem());
//                        campingRepository.saveAll(campings);
//                        System.out.println("Data fetched from API (page " + i + ") and saved to database successfully.");
//                    } else {
//                        System.out.println("No data found in API response (page " + i + ").");
//                    }
//                } else {
//                    System.out.println("Failed to fetch data from API (page " + i + "). Status code: " + responseEntity.getStatusCodeValue());
//                }
//            }
//        } catch (HttpClientErrorException ex) {
//            System.out.println("HTTP Client Error: " + ex.getStatusCode() + " - " + ex.getStatusText());
//        } catch (HttpServerErrorException ex) {
//            System.out.println("HTTP Server Error: " + ex.getStatusCode() + " - " + ex.getStatusText());
//        } catch (ResourceAccessException ex) {
//            System.out.println("Resource Access Error: " + ex.getMessage());
//        } catch (Exception e) {
//            System.out.println("Error occurred while fetching data from API and saving to database: " + e.getMessage());
//        }
//    }






//    private void fetchAndSaveData() {
//        int totalPages = 5; // 총 페이지 수
//        int itemsPerPage = 100; // 페이지당 항목 수
//
//        for (int pageNo = 1; pageNo <= totalPages; pageNo++) {
//            String apiUrl = String.format("https://apis.data.go.kr/B551011/GoCamping/basedList?numOfRows=%d&pageNo=%d&MobileOS=WIN&MobileApp=BreathoftheWIld&serviceKey=THdy3Wa2gPc%%2FhA6UmjpvfZ087NbuDZ2NfuvNd%%2FgLStW1jA%%2BViMfbfdYIdyX8upEoV16D9YRMZk7SEnU5FQcY7Q%%3D%%3D&_type=json", itemsPerPage, pageNo);
//
//            try {
//                RestTemplate restTemplate = new RestTemplate();
//                ResponseEntity<CampingResponse> responseEntity = restTemplate.getForEntity(apiUrl, CampingResponse.class);
//                CampingResponse campingResponse = responseEntity.getBody();
//
//                if (campingResponse != null && campingResponse.getResponse() != null && campingResponse.getResponse().getBody() != null &&
//                        campingResponse.getResponse().getBody().getItems() != null && campingResponse.getResponse().getBody().getItems().getItem() != null) {
//                    List<Camping> campingList = Arrays.asList(campingResponse.getResponse().getBody().getItems().getItem());
//                    campingRepository.saveAll(campingList);
//                }
//            } catch (Exception e) {
//                // 예외 처리 로직 추가
//                e.printStackTrace();
//            }
//        }
//    }



//    @PostConstruct
//    public void init() {
//
//        fetchAndSaveData();
//
//    }

//    @PostConstruct
//    public void initializeData() {
//        fetchDataAndSaveToDatabase();
//    }


    @Override
    public void fetchAndSaveData() {
        try {
            for (int pageNo = 1; pageNo <= 50; pageNo++) {
                String apiUrl = "https://apis.data.go.kr/B551011/GoCamping/basedList?numOfRows=1000&pageNo=" + pageNo + "&MobileOS=WIN&MobileApp=BreathoftheWIld&serviceKey=THdy3Wa2gPc%2FhA6UmjpvfZ087NbuDZ2NfuvNd%2FgLStW1jA%2BViMfbfdYIdyX8upEoV16D9YRMZk7SEnU5FQcY7Q%3D%3D&_type=json";
                String jsonData = fetchJsonDataFromApi(apiUrl);
                Camping[] campings = parseJsonData(jsonData);

                // 데이터가 없으면 페이지를 중단하고 다음 페이지로 넘어감
                if (campings.length == 0) {
                    System.out.println("No data found on page " + pageNo + ". Moving to the next page...");
                    continue;
                }

                saveCampingData(campings);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public String fetchJsonDataFromApi(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } finally {
            connection.disconnect();
        }
        return result.toString();
    }

    public Camping[] parseJsonData(String jsonData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CampingResponse response = objectMapper.readValue(jsonData, CampingResponse.class);
        if (response != null && response.getResponse() != null && response.getResponse().getBody() != null &&
                response.getResponse().getBody().getItems() != null && response.getResponse().getBody().getItems().getItem() != null) {
            return response.getResponse().getBody().getItems().getItem();
        } else {
            return new Camping[0];
        }
    }

    public void saveCampingData(Camping[] campings) {
        for (Camping camping : campings) {
            // intro가 최대 길이보다 길면 최대 길이까지 자르고 그렇지 않으면 그대로 사용합니다.
            int maxLength = 1500; //열의 최대 길이
            String intro = camping.getIntro();
            String direction = camping.getDirection();
            camping.setDirection(direction.length() > maxLength ? direction.substring(0, maxLength) : direction);
            camping.setIntro(intro.length() > maxLength ? intro.substring(0, maxLength) : intro);
        }
        campingRepository.saveAll(Arrays.asList(campings));
    }

//    @PostConstruct
//    @Scheduled
//    public void initializeData() {
//        fetchAndSaveData();
//    }

}

