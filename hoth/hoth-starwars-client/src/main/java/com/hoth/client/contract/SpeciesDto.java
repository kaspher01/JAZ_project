package com.hoth.client.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SpeciesDto {

    private String name;
    private String classification;
    private String designation;

    @JsonProperty("average_height")
    private String avgHeight;

    @JsonProperty("average_lifespan")
    private String avgLifespan;

    @JsonProperty("eye_colors")
    private String eyeColors;

    @JsonProperty("hair_colors")
    private String hairColors;

    @JsonProperty("skin_colors")
    private String skinColors;

    private String language;

    @JsonProperty("homeworld")
    private String homeworldUrl;

    @JsonProperty("people")
    private List<String> peopleUrls;

    @JsonProperty("films")
    private List<String> filmsUrls;

    private String url;
    private String created;
    private String edited;
}
