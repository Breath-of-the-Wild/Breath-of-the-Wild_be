package com.breath_of_the_wild_be.service.campingService;

import com.breath_of_the_wild_be.domain.Camping;
import com.breath_of_the_wild_be.domain.Festival;
import com.breath_of_the_wild_be.repository.campingRepository.CampingRepository;
import com.breath_of_the_wild_be.service.RegionMapperConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${campapi.apikey}")
    private String serviceKey;
    @Autowired
    public CampingServiceImpl(CampingRepository campingRepository) {
        this.campingRepository = campingRepository;
    }


    @Override
    public void fetchAndSaveData() {
        String baseUrl = "https://apis.data.go.kr/B551011/GoCamping/basedList";
        String numOfRows = "1000";
        String mobileOS = "WIN";
        String mobileApp = "BreathoftheWIld";

        String responseType = "json";

        try {
            for (int pageNo = 1; pageNo <= 50; pageNo++) {
                String apiUrl = String.format(
                        "%s?numOfRows=%s&pageNo=%d&MobileOS=%s&MobileApp=%s&serviceKey=%s&_type=%s",
                        baseUrl, numOfRows, pageNo, mobileOS, mobileApp, serviceKey, responseType
                );

                String jsonData = fetchJsonDataFromApi(apiUrl);
                Camping[] campings = parseJsonData(jsonData);

                // 데이터가 없으면 페이지를 중단하고 다음 페이지로 넘어감
                if (campings.length == 0) {
                    System.out.println("No data found on page " + pageNo + ". Moving to the next page...");
                    return;
                }

                saveCampingData(campings);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            // intro가 maxLength보다 길면 데이터 길이를 MaxLength까지 자름
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

    @Override
    public List<Camping> searchCampings(String searchType, String searchValue) {
        switch (searchType) {
            case "facltNm":
                return campingRepository.findByFacltNmContaining(searchValue);
            case "addr1":
                return campingRepository.findByAddr1Containing(searchValue);
            // Add more cases as needed
            default:
                throw new IllegalArgumentException("Unknown search type: " + searchType);
        }
    }

    @Override
    public Camping findByContentId(Long contentId) {
        Optional<Camping> campingOptional = campingRepository.findByContentId(contentId);
        return campingOptional.orElse(null); // Alternatively, handle it according to your requirements
    }

    public List<Camping> getCampingListByRegion(String region) {
        String mappedRegion = RegionMapperConfig.mapRegion(region);
        return campingRepository.findByDoNm(mappedRegion);
    }
}


