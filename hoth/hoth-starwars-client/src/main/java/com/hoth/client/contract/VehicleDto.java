package com.hoth.client.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VehicleDto extends GetIdClass {
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
