package com.breath_of_the_wild_be.service.festivalService;

import com.fasterxml.jackson.databind.JsonNode;

public interface FestivalService {
    void saveFestivals(JsonNode jsonNode);
}