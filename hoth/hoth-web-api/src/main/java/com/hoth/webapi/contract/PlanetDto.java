package com.hoth.webapi.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanetDto extends GetIdClass {

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

}
