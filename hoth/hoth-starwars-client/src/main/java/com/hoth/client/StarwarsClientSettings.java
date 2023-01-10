package com.hoth.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StarwarsClientSettings implements IStarwarsClientSettings{

    @Value("${starwars.api.host}")
    private String baseUrl;

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }
}
