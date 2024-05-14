package com.breath_of_the_wild_be.service.festivalService;

import com.breath_of_the_wild_be.domain.Festival;
import com.breath_of_the_wild_be.repository.festivalRepository.FestivalRepository;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FestivalServiceImpl implements FestivalService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FestivalRepository festivalRepository;

    @Override
    @PostConstruct
    public void fetchDataAndProcess() {
        final String baseUrl = "https://apis.data.go.kr/B551011/KorService1/searchFestival1?numOfRows=100&pageNo=%d&MobileOS=win&MobileApp=win&_type=json&arrange=Q&eventStartDate=20240401&serviceKey=tkpuYMyOJPiESQhzLecE1EshwjeUNeXfOJY7y8Rku7L2kh5E%2FbSH7NC7CZ1vvthRi72%2FidxEOUL%2FULnq0WWkHw%3D%3D";
        int totalPages = 5;

        for (int pageNo = 1; pageNo <= totalPages; pageNo++) {
            try {
                String apiUrl = String.format(baseUrl, pageNo);
                JsonNode response = restTemplate.getForObject(apiUrl, JsonNode.class);
                saveFestivals(response.path("response").path("body").path("items"));
            } catch (Exception e) {
                System.err.println("An error occurred while fetching and processing festival data for page " + pageNo + ": " + e.getMessage());
            }
        }
    }

    @Override
    public void saveFestivals(JsonNode jsonNode) {
        List<Festival> festivals = new ArrayList<>();

        for (JsonNode festivalNode : jsonNode) {
            Festival festival = new Festival();
            // 각 필드에 대한 값 설정
            // 예: festival.setTitle(getTextValueOrNull(festivalNode, "title"));
            // 필드 값 설정 코드 여기에 추가

            festivals.add(festival);
        }

        festivalRepository.saveAll(festivals);
    }

    private String getTextValueOrNull(JsonNode node, String fieldName) {
        JsonNode fieldValue = node.get(fieldName);
        return (fieldValue != null && !fieldValue.isNull()) ? fieldValue.asText() : null;
    }


}
