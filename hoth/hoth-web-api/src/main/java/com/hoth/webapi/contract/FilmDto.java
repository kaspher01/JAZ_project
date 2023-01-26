package com.hoth.webapi.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmDto extends GetIdClass {

    private String title;

    @JsonProperty("episode_id")
    private int episodeId;

    private String director;

    private String producer;

    @JsonProperty("release_date")
    private Date releaseDate;

    @JsonProperty("species")
    private List<SpeciesDto> species;

    @JsonProperty("starships")
    private List<StarshipDto> starships;

    @JsonProperty("vehicles")
    private List<VehicleDto> vehicles;

    @JsonProperty("characters")
    private List<PersonDto> characters;

    @JsonProperty("planets")
    private List<PlanetDto> planets;

}
