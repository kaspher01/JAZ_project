package com.hoth.client.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlanetDto {

    private String name;

    private String diameter;

    @JsonProperty("rotation_period")
    private String rotationPeriod;

    @JsonProperty("orbital_period")
    private String orbitalPeriod;

    private String gravity;

    private String population;

    private String climate;

    private String terrain;

    @JsonProperty("surface_water")
    private String surfaceWater;

    @JsonProperty("residents")
    private List<String> residentsUrls;

    @JsonProperty("films")
    private List<String> filmsUrls;

    private String url;
    private String created;
    private String edited;
}
