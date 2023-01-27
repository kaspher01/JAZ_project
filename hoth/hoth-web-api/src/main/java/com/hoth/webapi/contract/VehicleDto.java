package com.hoth.webapi.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto{
    private String name;
    private String model;

    @JsonProperty("vehicle_class")
    private String vehicleClass;
    private String manufacturer;

    private String length;

    @JsonProperty("cost_in_credits")
    private String costInCredits;

    private String crew;
    private String passengers;

    @JsonProperty("max_atmosphering_speed")
    private String maxAtmospheringSpeed;

}
