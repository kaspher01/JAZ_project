package com.hoth.data.repositories;

public interface ICatalogData {
    FilmRepository getFilms();
    PersonRepository getPeople();
    PlanetRepository getPlanets();
    SpeciesRepository getSpecies();
    StarshipRepository getStarships();
    VehicleRepository getVehicles();
}
