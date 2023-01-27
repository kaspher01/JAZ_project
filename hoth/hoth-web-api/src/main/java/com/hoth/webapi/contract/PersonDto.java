package com.hoth.webapi.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto{

    private String name;

    @JsonProperty("birth_year")
    private String birthYear;

    private String gender;

    private String height;

    private String mass;

    @JsonProperty("skin_color")
    private String skinColor;

}
