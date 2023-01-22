package com.hoth.client;

import com.hoth.client.contract.*;

public interface IStarwarsClient {

    ResultDto getFilms();
    FilmDto getFilm(int id);
    PersonDto getPerson(int id);
    StarshipDto getStarship(int id);
    VehicleDto getVehicle(int id);
    SpeciesDto getSpecies(int id);
    PlanetDto getPlanet(int id);
}
