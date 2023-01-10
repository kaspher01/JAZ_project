package com.hoth.client;

import com.hoth.client.contract.FilmDto;
import com.hoth.client.contract.PersonDto;
import com.hoth.client.contract.ResultDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StarwarsClient implements IStarwarsClient {

    RestTemplate restClient;
    String baseUrl;
    private final IStarwarsClientSettings _settings;

    public StarwarsClient(IStarwarsClientSettings settings) {
        restClient = new RestTemplate();
        this.baseUrl = settings.getBaseUrl();
        _settings = settings;
    }

    @Override
    public ResultDto getFilms() {
        String url = _settings.getUrlBuilder()
                .pathSegment("films/")
                .queryParam("format", "json")
                .build()
                .toUriString();
        return restClient.getForObject(url, ResultDto.class);
    }

    @Override
    public FilmDto getFilm(int id) {
        String url = _settings.getUrlBuilder()
                .pathSegment("films", id+"/")
                .queryParam("format", "json")
                .build()
                .toUriString();
        return restClient.getForEntity(url, FilmDto.class).getBody();
    }

    @Override
    public PersonDto getPerson(int id) {
        String url = _settings.getUrlBuilder()
                .pathSegment("people", id+"/")
                .queryParam("format", "json")
                .build()
                .toUriString();
        return restClient.getForEntity(url, PersonDto.class).getBody();
    }
}
