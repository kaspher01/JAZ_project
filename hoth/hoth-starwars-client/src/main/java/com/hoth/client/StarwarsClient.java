package com.hoth.client;

import com.hoth.client.contract.*;
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

    public String buildUrl(String entityName, int id){
        return _settings.getUrlBuilder()
                .pathSegment(entityName, id+"/")
                .queryParam("format", "json")
                .build()
                .toUriString();
    }

    @Override
    public FilmDto getFilm(int id) {
        String url = buildUrl("films", id);
        return restClient.getForEntity(url, FilmDto.class).getBody();
    }

    @Override
    public PersonDto getPerson(int id) {
        String url = buildUrl("people", id);
        return restClient.getForEntity(url, PersonDto.class).getBody();
    }

    @Override
    public StarshipDto getStarship(int id) {
        String url = buildUrl("starships", id);
        return restClient.getForEntity(url, StarshipDto.class).getBody();
    }

    @Override
    public VehicleDto getVehicle(int id) {
        String url = buildUrl("vehicles", id);
        return restClient.getForEntity(url, VehicleDto.class).getBody();
    }

    @Override
    public SpeciesDto getSpecies(int id) {
        String url = buildUrl("species", id);
        return restClient.getForEntity(url, SpeciesDto.class).getBody();
    }

    @Override
    public PlanetDto getPlanet(int id) {
        String url = buildUrl("planets", id);
        return restClient.getForEntity(url, PlanetDto.class).getBody();
    }
}
