package com.breath_of_the_wild_be.service.festivalService;

import com.breath_of_the_wild_be.domain.Festival;
import com.breath_of_the_wild_be.repository.festivalRepository.FestivalRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FestivalServiceImpl implements FestivalService {

    private final FestivalRepository festivalRepository;

    @Autowired
    public FestivalServiceImpl(FestivalRepository festivalRepository) {
        this.festivalRepository = festivalRepository;
    }

    @Override
    public void saveFestivals(JsonNode jsonNode) {
//        List<Festival> festivals = new ArrayList<>();
//
//        for (JsonNode festivalNode : jsonNode) {
//            Festival festival = new Festival();
//            festival.setAddr1(festivalNode.get("addr1").asText());
//            festival.setAddr2(festivalNode.get("addr2").asText());
//            // Set other fields accordingly
//
//            // Set created date as current date
//            festival.setCreatedDate(new Date());
//
//            festivals.add(festival);
//        }
//
//        festivalRepository.saveAll(festivals);
    }
}
