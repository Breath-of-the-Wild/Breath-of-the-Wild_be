package com.breath_of_the_wild_be.dto.oauth2;

public interface OAuth2Response {
    String getProvider();
    String getProviderId();
    String getEmail();
    String getName();
}
