package com.hoth.webapi.services.utilis;

import com.hoth.data.model.*;
import com.hoth.webapi.contract.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper implements ICatalogDtoMappers{
    private final IMapDtos<Person, PersonDto> forPerson;
    private final IMapDtos<Planet, PlanetDto> forPlanet;
    private final IMapDtos<Species, SpeciesDto> forSpecies;
    private final IMapDtos<Vehicle, VehicleDto> forVehicle;
    private final IMapDtos<Starship, StarshipDto> forStarship;

    @Autowired
    public DtoMapper(IMapDtos<Person, PersonDto> forPerson, IMapDtos<Planet, PlanetDto> forPlanet, IMapDtos<Species, SpeciesDto> forSpecies, IMapDtos<Vehicle, VehicleDto> forVehicle, IMapDtos<Starship, StarshipDto> forStarship) {
        this.forPerson = forPerson;
        this.forPlanet = forPlanet;
        this.forSpecies = forSpecies;
        this.forVehicle = forVehicle;
        this.forStarship = forStarship;
    }


    @Override
    public IMapDtos<Species, SpeciesDto> forSpecies() {
        return forSpecies;
    }

    @Override
    public IMapDtos<Starship, StarshipDto> forStarships() {
        return forStarship;
    }

    @Override
    public IMapDtos<Vehicle, VehicleDto> forVehicles() {
        return forVehicle;
    }

    @Override
    public IMapDtos<Person, PersonDto> forCharacters() {
        return forPerson;
    }

    @Override
    public IMapDtos<Planet, PlanetDto> forPlanets() {
        return forPlanet;
    }
}
