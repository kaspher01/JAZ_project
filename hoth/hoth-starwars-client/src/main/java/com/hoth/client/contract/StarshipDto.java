package com.hoth.client.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StarshipDto extends GetIdClass{

    private String name;
    private String model;

    @JsonProperty("starship_class")
    private String starshipClass;
    private String manufacturer;

    @JsonProperty("cost_in_credits")
    private String costInCredits;
    private String length;
    private String crew;
    private String passengers;

    @JsonProperty("max_atmosphering_speed")
    private String maxAtmospheringSpeed;

    @JsonProperty("hyperdrive_rating")
    private String hyperdriveRating;

    @JsonProperty("MGLT")
    private String maxMegalights;

    @JsonProperty("cargo_capacity")
    private String cargoCapacity;
    private String consumables;

    @JsonProperty("films")
    private List<String> filmsUrls;

    @JsonProperty("pilots")
    private List<String> pilotsUrls;

    private String created;
    private String edited;

}
