package com.breath_of_the_wild_be.service.campingService;

import com.breath_of_the_wild_be.domain.Camping;
import com.breath_of_the_wild_be.repository.campingRepository.CampingRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    @Override
    public void fetchDataAndSaveToDatabase() {
        int numOfRows = 50;
        String mobileOS = "WIN";
        String mobileApp = "BreathoftheWile";
        String serviceKey = "THdy3Wa2gPc%2FhA6UmjpvfZ087NbuDZ2NfuvNd%2FgLStW1jA%2BViMfbfdYIdyX8upEoV16D9YRMZk7SEnU5FQcY7Q%3D%3D";
        String format = "json";

        RestTemplate restTemplate = new RestTemplate();
        try {
            for (int i = 1; i <= 100; i++) { // pageNo를 1부터 100까지 반복
                String apiUrl = String.format("https://apis.data.go.kr/B551011/GoCamping/basedList?numOfRows=%d&pageNo=%d&MobileOS=%s&MobileApp=%s&serviceKey=%s&_type=%s",
                        numOfRows, i, mobileOS, mobileApp, serviceKey, format);

                // API 응답을 가져옵니다.
                ResponseEntity<CampingResponse> responseEntity = restTemplate.getForEntity(apiUrl, CampingResponse.class);

                // 응답 코드 확인
                if (responseEntity.getStatusCode().is2xxSuccessful()) {
                    CampingResponse response = responseEntity.getBody();
                    if (response != null && response.getResponse() != null && response.getResponse().getBody() != null &&
                            response.getResponse().getBody().getItems() != null && response.getResponse().getBody().getItems().getItem() != null) {
                        // CampingResponse에서 직접 Camping으로 변환하여 저장합니다.
                        List<Camping> campings = Arrays.asList(response.getResponse().getBody().getItems().getItem());
                        campingRepository.saveAll(campings);
                        System.out.println("Data fetched from API (page " + i + ") and saved to database successfully.");
                    } else {
                        System.out.println("No data found in API response (page " + i + ").");
                    }
                } else {
                    System.out.println("Failed to fetch data from API (page " + i + "). Status code: " + responseEntity.getStatusCodeValue());
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred while fetching data from API and saving to database: " + e.getMessage());
        }
    }


    @PostConstruct
    public void initializeData() {
        fetchDataAndSaveToDatabase();
    }


}

