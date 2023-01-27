package com.hoth.webapi.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpeciesDto{

    private String name;
    private String classification;
    private String designation;

    @JsonProperty("average_height")
    private String avgHeight;

    @JsonProperty("average_lifespan")
    private String avgLifespan;

    @JsonProperty("eye_colors")
    private String eyeColors;

    @JsonProperty("skin_colors")
    private String skinColors;

    private String language;
}
