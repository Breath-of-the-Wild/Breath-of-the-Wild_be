package com.breath_of_the_wild_be.service.festivalService;
import com.breath_of_the_wild_be.domain.Festival;
import com.breath_of_the_wild_be.repository.festivalRepository.FestivalRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FestivalServiceImpl implements FestivalService {


    @Value("${festivalapi.apikey}")
    private String serviceKey;

    @Autowired
    private FestivalRepository festivalRepository;

    @Override
    public List<Festival> getAllFestivals() {
        return festivalRepository.findAll();
    }

    @Override
    public void fetchAndSaveData() {
        String baseUrl = "https://apis.data.go.kr/B551011/KorService1/searchFestival1";
        String numOfRows = "100";
        String mobileOS = "win";
        String mobileApp = "win";
        String responseType = "json";
        String arrange = "Q";
        String eventStartDate = "20240401";


        try {
            for (int pageNo = 1; pageNo <= 50; pageNo++) {
                String apiUrl = String.format(
                        "%s?numOfRows=%s&pageNo=%d&MobileOS=%s&MobileApp=%s&_type=%s&arrange=%s&eventStartDate=%s&serviceKey=%s",
                        baseUrl, numOfRows, pageNo, mobileOS, mobileApp, responseType, arrange, eventStartDate, serviceKey
                );

                String jsonData = fetchJsonDataFromApi(apiUrl);
                Festival[] festivals = parseJsonData(jsonData);

                // 데이터가 없으면 중단하고 다음 페이지로 넘어감
                if (festivals.length == 0) {
                    System.out.println("No data found on page " + pageNo + ". Moving to the next page...");
                    return;
                }

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
        for (Festival festival : festivals) {
            Optional<Festival> existingFestivalOptional = festivalRepository.findByContentid(festival.getContentid());
            if (existingFestivalOptional.isPresent()) {
                Festival existingFestival = existingFestivalOptional.get();
                updateFestival(existingFestival, festival);
                festivalRepository.save(existingFestival);
            } else {
                festivalRepository.save(festival);
            }
        }
    }

    private void updateFestival(Festival existingFestival, Festival newFestival) {
        existingFestival.setAddr1(newFestival.getAddr1());
        existingFestival.setAddr2(newFestival.getAddr2());
        existingFestival.setBooktour(newFestival.getBooktour());
        existingFestival.setCat1(newFestival.getCat1());
        existingFestival.setCat2(newFestival.getCat2());
        existingFestival.setCat3(newFestival.getCat3());
        existingFestival.setContenttypeid(newFestival.getContenttypeid());
        existingFestival.setCreatedtime(newFestival.getCreatedtime());
        existingFestival.setEventstartdate(newFestival.getEventstartdate());
        existingFestival.setEventenddate(newFestival.getEventenddate());
        existingFestival.setFirstimage(newFestival.getFirstimage());
        existingFestival.setFirstimage2(newFestival.getFirstimage2());
        existingFestival.setCpyrhtDivCd(newFestival.getCpyrhtDivCd());
        existingFestival.setMapx(newFestival.getMapx());
        existingFestival.setMapy(newFestival.getMapy());
        existingFestival.setMlevel(newFestival.getMlevel());
        existingFestival.setModifiedtime(newFestival.getModifiedtime());
        existingFestival.setAreacode(newFestival.getAreacode());
        existingFestival.setSigungucode(newFestival.getSigungucode());
        existingFestival.setTel(newFestival.getTel());
        existingFestival.setTitle(newFestival.getTitle());
    }

    @Override
    public Festival findByContentId(String contentid) {
        Optional<Festival> festivalOptional = festivalRepository.findByContentid(contentid);
        return festivalOptional.orElse(null); // 혹은 원하는 대응 방법에 따라 처리
    }

    @Override
    public List<Festival> searchFestival(String searchType, String searchValue) {
        switch (searchType) {
            case "title":
                return festivalRepository.findByTitleContaining(searchValue);
            case "addr1":
                return festivalRepository.findByAddr1Containing(searchValue);
            // Add more cases as needed
            default:
                throw new IllegalArgumentException("Unknown search type: " + searchType);
        }
    }
}