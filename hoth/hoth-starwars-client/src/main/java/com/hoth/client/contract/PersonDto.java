package com.hoth.client.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class PersonDto extends GetIdClass{

    private String name;

    @JsonProperty("birth_year")
    private String birthYear;

    @JsonProperty("eye_color")
    private String eyeColor;

    private String gender;

    @JsonProperty("hair_color")
    private String hairColor;

    private String height;

    private String mass;

    @JsonProperty("skin_color")
    private String skinColor;

    @JsonProperty("homeworld")
    private String homeworldUrl;

    @JsonProperty("species")
    private List<String> speciesUrls;

    @JsonProperty("starships")
    private List<String> starshipsUrls;

    @JsonProperty("vehicles")
    private List<String> vehiclesUrls;

    private String created;
    private String edited;


}
