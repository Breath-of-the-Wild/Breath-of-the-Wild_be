package com.breath_of_the_wild_be.service.festivalService;

import com.breath_of_the_wild_be.domain.Festival;
import com.breath_of_the_wild_be.repository.festivalRepository.FestivalRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FestivalServiceImpl implements FestivalService {


    @Autowired
    private FestivalRepository festivalRepository;

    @Override
    public List<Festival> getAllFestivals() {
        return festivalRepository.findAll();
    }
    @Override
    public void fetchAndSaveData() {
        try {
            for (int pageNo = 1; pageNo <= 50; pageNo++) {
                String apiUrl = "https://apis.data.go.kr/B551011/KorService1/searchFestival1?numOfRows=100&pageNo=" + pageNo + "&MobileOS=win&MobileApp=win&_type=json&arrange=Q&eventStartDate=20240401&serviceKey=tkpuYMyOJPiESQhzLecE1EshwjeUNeXfOJY7y8Rku7L2kh5E%2FbSH7NC7CZ1vvthRi72%2FidxEOUL%2FULnq0WWkHw%3D%3D";
                String jsonData = fetchJsonDataFromApi(apiUrl);
                Festival[] festivals = parseJsonData(jsonData);
                saveFestivalData(festivals);
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

    public Festival[] parseJsonData(String jsonData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FestivalResponse response = objectMapper.readValue(jsonData, FestivalResponse.class);
        if (response != null && response.getResponse() != null && response.getResponse().getBody() != null &&
                response.getResponse().getBody().getItems() != null && response.getResponse().getBody().getItems().getItem() != null) {
            return response.getResponse().getBody().getItems().getItem();
        } else {
            return new Festival[0];
        }
    }

    public void saveFestivalData(Festival[] festivals) {

        festivalRepository.saveAll(Arrays.asList(festivals));
    }
}
