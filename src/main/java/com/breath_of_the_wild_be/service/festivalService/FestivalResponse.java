package com.breath_of_the_wild_be.service.festivalService;

import com.breath_of_the_wild_be.config.FestivalItemsDeserializer;
import com.breath_of_the_wild_be.config.ItemsDeserializer;
import com.breath_of_the_wild_be.domain.Festival;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FestivalResponse {

    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Response {
        private Body body;

        public Body getBody() {
            return body;
        }

        public void setBody(Body body) {
            this.body = body;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Body {
        @JsonDeserialize(using = FestivalItemsDeserializer.class)
        private Items items;

        public Items getItems() {
            return items;
        }

        public void setItems(Items items) {
            this.items = items;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Items {
        private Festival[] item;

        public Festival[] getItem() {
            return item;
        }

        public void setItem(Festival[] item) {
            this.item = item;
        }
    }
}
