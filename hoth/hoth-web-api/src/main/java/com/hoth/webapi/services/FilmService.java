package com.hoth.webapi.services;

import com.hoth.data.model.*;
import com.hoth.data.repositories.ICatalogData;
import com.hoth.webapi.services.utilis.ICatalogDtoMappers;


import com.hoth.webapi.contract.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService implements IFilmService{
    private final ICatalogData db;
    private final ICatalogDtoMappers mapper;

    @Autowired
    public FilmService(ICatalogData db, ICatalogDtoMappers mapper) {
        this.db = db;
        this.mapper = mapper;
    }

    @Override
    public List<FilmDto> getAll() {
        List<FilmDto> results = new ArrayList<>();
        List<Film> films = db.getFilms().findAll();
        for(Film film : films){

            List<SpeciesDto> speciesDtos = new ArrayList<>();
            for(Species species : film.getSpecies())
                speciesDtos.add(mapper.forSpecies().map(species));

            List<StarshipDto> starshipDtos = new ArrayList<>();
            for(Starship starship : film.getStarships())
                starshipDtos.add(mapper.forStarships().map(starship));

            List<VehicleDto> vehicleDtos = new ArrayList<>();
            for(Vehicle vehicle : film.getVehicles())
                vehicleDtos.add(mapper.forVehicles().map(vehicle));

            List<PersonDto> peopleDtos = new ArrayList<>();
            for(Person person : film.getCharacters())
                peopleDtos.add(mapper.forCharacters().map(person));

            List<PlanetDto> planetDtos = new ArrayList<>();
            for(Planet planet : film.getPlanets())
                planetDtos.add(mapper.forPlanets().map(planet));

            FilmDto filmDto = new FilmDto(
                    film.getTitle(),
                    film.getEpisodeId(),
                    film.getDirector(),
                    film.getProducer(),
                    film.getReleaseDate(),
                    speciesDtos,
                    starshipDtos,
                    vehicleDtos,
                    peopleDtos,
                    planetDtos
                    );
            results.add(filmDto);
        }
        return results;
    }


}
