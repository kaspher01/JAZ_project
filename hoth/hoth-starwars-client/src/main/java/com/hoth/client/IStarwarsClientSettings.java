package com.hoth.client;

import org.springframework.web.util.UriComponentsBuilder;

public interface IStarwarsClientSettings {

    String getBaseUrl();

    default UriComponentsBuilder getUrlBuilder(){
        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(getBaseUrl());
    }
}
