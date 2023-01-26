package com.hoth.webapi.services.utilis;


import com.hoth.data.model.*;
import com.hoth.webapi.contract.*;
import org.springframework.stereotype.Component;

@Component
public interface ICatalogDtoMappers {
    IMapDtos<Species, SpeciesDto> forSpecies();

    IMapDtos<Starship, StarshipDto> forStarships();

    IMapDtos<Vehicle, VehicleDto> forVehicles();

    IMapDtos<Person, PersonDto> forCharacters();

    IMapDtos<Planet, PlanetDto> forPlanets();
}
