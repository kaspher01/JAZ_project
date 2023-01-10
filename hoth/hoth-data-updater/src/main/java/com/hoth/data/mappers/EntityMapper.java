package com.hoth.data.mappers;

import com.hoth.client.contract.*;
import com.hoth.data.model.*;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper implements ICatalogMappers{
    private final IMapEntities<FilmDto, Film> forFilm;
    private final IMapEntities<PersonDto, Person> forPerson;
    private final IMapEntities<PlanetDto, Planet> forPlanet;
    private final IMapEntities<SpeciesDto, Species> forSpecies;
    private final IMapEntities<StarshipDto, Starship> forStarship;
    private final IMapEntities<VehicleDto, Vehicle> forVehicle;

    public EntityMapper(IMapEntities<FilmDto, Film> forFilm, IMapEntities<PersonDto, Person> forPerson, IMapEntities<PlanetDto, Planet> forPlanet, IMapEntities<SpeciesDto, Species> forSpecies, IMapEntities<StarshipDto, Starship> forStarship, IMapEntities<VehicleDto, Vehicle> forVehicle) {
        this.forFilm = forFilm;
        this.forPerson = forPerson;
        this.forPlanet = forPlanet;
        this.forSpecies = forSpecies;
        this.forStarship = forStarship;
        this.forVehicle = forVehicle;
    }

    @Override
    public IMapEntities<FilmDto, Film> forFilm() {
        return forFilm;
    }

    @Override
    public IMapEntities<PersonDto, Person> forPerson() {
        return forPerson;
    }

    @Override
    public IMapEntities<PlanetDto, Planet> forPlanet() {
        return forPlanet;
    }

    @Override
    public IMapEntities<SpeciesDto, Species> forSpecies() {
        return forSpecies;
    }

    @Override
    public IMapEntities<StarshipDto, Starship> forStarship() {
        return forStarship;
    }

    @Override
    public IMapEntities<VehicleDto, Vehicle> forVehicle() {
        return forVehicle;
    }
}
