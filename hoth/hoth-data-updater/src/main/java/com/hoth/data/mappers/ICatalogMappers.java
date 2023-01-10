package com.hoth.data.mappers;

import com.hoth.client.contract.*;
import com.hoth.data.model.*;

public interface ICatalogMappers {
    IMapEntities<FilmDto, Film> forFilm();
    IMapEntities<PersonDto, Person> forPerson();
    IMapEntities<PlanetDto, Planet> forPlanet();
    IMapEntities<SpeciesDto, Species> forSpecies();
    IMapEntities<StarshipDto, Starship> forStarship();
    IMapEntities<VehicleDto, Vehicle> forVehicle();
}
