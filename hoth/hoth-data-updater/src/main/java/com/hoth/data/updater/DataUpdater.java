package com.hoth.data.updater;

import com.hoth.client.IStarwarsClient;
import com.hoth.client.contract.*;
import com.hoth.data.mappers.ICatalogMappers;
import com.hoth.data.model.*;
import com.hoth.data.repositories.ICatalogData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class DataUpdater implements IUpdateData {
    private final ICatalogData data;
    private final IStarwarsClient client;
    private final ICatalogMappers entityMapper;


    public DataUpdater(ICatalogData data, IStarwarsClient client, ICatalogMappers entityMapper) {
        this.data = data;
        this.client = client;
        this.entityMapper = entityMapper;
    }

    @Override
    public void updateEverything() {
        var resultDto = client.getFilms();

        var films = resultDto.getResults()
                .stream()
                .map(dto -> client.getFilm(dto.getId()))
                .toList();

        var entities = films
                .stream()
                .map(dto -> entityMapper.forFilm().map(dto))
                .toList();

        var filmsTitles = data.getFilms()
                .findAll()
                .stream()
                .map(Film::getTitle)
                .toList();

        var entitiesToSave = entities
                .stream()
                .filter(Predicate.not(entity -> filmsTitles.contains(entity.getTitle())))
                .toList();



        entitiesToSave.forEach(x -> {
            data.getFilms().save(x);

            var filmDto = films
                    .stream()
                    .filter(dto -> Objects.equals(dto.getTitle(), x.getTitle()))
                    .findFirst()
                    .get();


            List<PersonDto> peopleDto = new ArrayList<>();

            filmDto.getCharactersUrls()
                    .forEach(l -> peopleDto.add(
                            client.getPerson(Integer.parseInt(l.replaceFirst(".*/([^/?]+).*", "$1")))
                    ));

            peopleDto.forEach((personDto) -> {
                var existingPerson = data.getPeople().findPersonByName(personDto.getName());

                var vehicleDtos = personDto.getVehiclesUrls()
                        .stream()
                        .map(l -> client.getVehicle(Integer.parseInt(l.replaceFirst(".*/([^/?]+).*", "$1"))))
                        .toList();

                var starshipsDtos = personDto.getStarshipsUrls()
                        .stream()
                        .map(l -> client.getStarship(Integer.parseInt(l.replaceFirst(".*/([^/?]+).*", "$1"))))
                        .toList();

                var speciesDtos = personDto.getSpeciesUrls()
                        .stream()
                        .map(l -> client.getSpecies(Integer.parseInt(l.replaceFirst(".*/([^/?]+).*", "$1"))))
                        .toList();

                var planetDto = client.getPlanet(Integer.parseInt(personDto.getHomeworldUrl().replaceFirst(".*/([^/?]+).*", "$1")));

                if(existingPerson.isPresent()){
                    for(VehicleDto vehicleDto : vehicleDtos){
                        var existingVehicle = data.getVehicles().findVehicleByName(vehicleDto.getName());
                        if(existingVehicle.isPresent()){
                            existingVehicle.get().getPilots().add(existingPerson.get());

                            if(!x.getVehicles().contains(existingVehicle.get()))
                                existingVehicle.get().getFilms().add(x);

                            data.getVehicles().save(existingVehicle.get());
                        } else {
                            var vehicle = entityMapper.forVehicle().map(vehicleDto);

                            vehicle.getPilots().add(existingPerson.get());

                            if(!x.getVehicles().contains(vehicle))
                                vehicle.getFilms().add(x);

                            data.getVehicles().save(vehicle);
                        }
                    }
                    for(StarshipDto starshipDto : starshipsDtos){
                        var existingStarship = data.getStarships().findStarshipByName(starshipDto.getName());
                        if(existingStarship.isPresent()){
                            existingStarship.get().getPilots().add(existingPerson.get());

                            if(!x.getStarships().contains(existingStarship.get()))
                                existingStarship.get().getFilms().add(x);

                            data.getStarships().save(existingStarship.get());
                        } else {
                            var starship = entityMapper.forStarship().map(starshipDto);
                            starship.getPilots().add(existingPerson.get());

                            if(!x.getStarships().contains(starship))
                                starship.getFilms().add(x);

                            data.getStarships().save(starship);
                        }

                    }
                    for(SpeciesDto speciesDto : speciesDtos){
                        var existingSpecies = data.getSpecies().findSpeciesByName(speciesDto.getName());
                        if(existingSpecies.isPresent()){
                            existingSpecies.get().getPeople().add(existingPerson.get());

                            if(!x.getSpecies().contains(existingSpecies.get()))
                                existingSpecies.get().getFilms().add(x);

                            data.getSpecies().save(existingSpecies.get());
                        } else {
                            var species = entityMapper.forSpecies().map(speciesDto);
                            species.getPeople().add(existingPerson.get());

                            if(!x.getSpecies().contains(species))
                                species.getFilms().add(x);

                            data.getSpecies().save(species);
                        }
                    }

                    var existingPlanet = data.getPlanets().findPlanetByName(planetDto.getName());
                    if(existingPlanet.isPresent()){
                        existingPlanet.get().getResidents().add(existingPerson.get());

                        if(!x.getPlanets().contains(existingPlanet.get()))
                            existingPlanet.get().getFilms().add(x);

                        data.getPlanets().save(existingPlanet.get());
                    }
                    else {
                        var planet = entityMapper.forPlanet().map(planetDto);
                        planet.getResidents().add(existingPerson.get());

                        if(!x.getPlanets().contains(planet))
                            planet.getFilms().add(x);

                        data.getPlanets().save(planet);

                    }
                    data.getPeople().save(existingPerson.get());
                    existingPerson.get().getFilms().add(x);
                    data.getFilms().save(x);
                }
                else {
                    var person = entityMapper.forPerson().map(personDto);
                    data.getPeople().save(person);

                    for(VehicleDto vehicleDto : vehicleDtos){
                        var existingVehicle = data.getVehicles().findVehicleByName(vehicleDto.getName());
                        if(existingVehicle.isPresent()){
                            existingVehicle.get().getPilots().add(person);

                            if(!x.getVehicles().contains(existingVehicle.get()))
                                existingVehicle.get().getFilms().add(x);

                            data.getVehicles().save(existingVehicle.get());
                        } else {
                            var vehicle = entityMapper.forVehicle().map(vehicleDto);
                            vehicle.getPilots().add(person);

                            if(!x.getVehicles().contains(vehicle))
                                vehicle.getFilms().add(x);

                            data.getVehicles().save(vehicle);
                        }
                    }
                    for(StarshipDto starshipDto : starshipsDtos){
                        var existingStarship = data.getStarships().findStarshipByName(starshipDto.getName());
                        if(existingStarship.isPresent()){
                            existingStarship.get().getPilots().add(person);

                            if(!x.getStarships().contains(existingStarship.get()))
                                existingStarship.get().getFilms().add(x);

                            data.getStarships().save(existingStarship.get());
                        } else {
                            var starship = entityMapper.forStarship().map(starshipDto);
                            starship.getPilots().add(person);

                            if(!x.getStarships().contains(starship))
                                starship.getFilms().add(x);

                            data.getStarships().save(starship);
                        }
                    }
                    for(SpeciesDto speciesDto : speciesDtos){
                        var existingSpecies = data.getSpecies().findSpeciesByName(speciesDto.getName());
                        if(existingSpecies.isPresent()){
                            existingSpecies.get().getPeople().add(person);
                            if(!x.getSpecies().contains(existingSpecies.get()))
                                existingSpecies.get().getFilms().add(x);
                            data.getSpecies().save(existingSpecies.get());
                        } else {
                            var species = entityMapper.forSpecies().map(speciesDto);
                            species.getPeople().add(person);
                            if(!x.getSpecies().contains(species))
                                species.getFilms().add(x);
                            data.getSpecies().save(species);
                        }

                    }

                    var existingPlanet = data.getPlanets().findPlanetByName(planetDto.getName());
                    if(existingPlanet.isPresent()){
                        existingPlanet.get().getResidents().add(person);
                        person.setHomeworld(existingPlanet.get());

                        if(!x.getPlanets().contains(existingPlanet.get()))
                            existingPlanet.get().getFilms().add(x);

                        data.getPlanets().save(existingPlanet.get());
                    }
                    else {
                        var planet = entityMapper.forPlanet().map(planetDto);
                        planet.getResidents().add(person);
                        person.setHomeworld(planet);
                        if(!x.getPlanets().contains(planet))
                            planet.getFilms().add(x);

                        data.getPlanets().save(planet);

                    }

                    data.getPeople().save(person);
                    person.getFilms().add(x);
                    data.getFilms().save(x);
                }
            });

            data.getFilms().save(x);

        });
    }
}