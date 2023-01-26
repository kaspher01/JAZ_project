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
                        .map(l -> client.getVehicle(Integer.parseInt(l.replaceFirst(".*\\/(\\d+)\\/$", "$1"))))
                        .toList();

                var starshipsDtos = personDto.getStarshipsUrls()
                        .stream()
                        .map(l -> client.getStarship(Integer.parseInt(l.replaceFirst(".*\\/(\\d+)\\/$", "$1"))))
                        .toList();

                var speciesDtos = personDto.getSpeciesUrls()
                        .stream()
                        .map(l -> client.getSpecies(Integer.parseInt(l.replaceFirst(".*\\/(\\d+)\\/$", "$1"))))
                        .toList();

                if(existingPerson.isPresent()){

                    for(VehicleDto vehicleDto : vehicleDtos){
                        var existingVehicle = data.getVehicles().findVehicleByName(vehicleDto.getName());
                        if(existingVehicle.isPresent()){
                            existingVehicle.get().getPilots().add(existingPerson.get());
                            existingPerson.get().getVehicles().add(existingVehicle.get());

                            if(!x.getVehicles().contains(existingVehicle.get())){
                                x.getVehicles().add(existingVehicle.get());
                                existingVehicle.get().getFilms().add(x);
                            }

                            data.getVehicles().save(existingVehicle.get());
                        } else {
                            var vehicle = entityMapper.forVehicle().map(vehicleDto);

                            vehicle.getPilots().add(existingPerson.get());
                            existingPerson.get().getVehicles().add(vehicle);

                            if(!x.getVehicles().contains(vehicle)){
                                x.getVehicles().add(vehicle);
                                vehicle.getFilms().add(x);
                            }

                            data.getVehicles().save(vehicle);
                        }
                    }
                    for(StarshipDto starshipDto : starshipsDtos){
                        var existingStarship = data.getStarships().findStarshipByName(starshipDto.getName());
                        if(existingStarship.isPresent()){
                            existingStarship.get().getPilots().add(existingPerson.get());
                            existingPerson.get().getStarships().add(existingStarship.get());

                            if(!x.getStarships().contains(existingStarship.get())){
                                x.getStarships().add(existingStarship.get());
                                existingStarship.get().getFilms().add(x);
                            }

                            data.getStarships().save(existingStarship.get());
                        } else {
                            var starship = entityMapper.forStarship().map(starshipDto);
                            starship.getPilots().add(existingPerson.get());
                            existingPerson.get().getStarships().add(starship);

                            if(!x.getStarships().contains(starship)){
                                x.getStarships().add(starship);
                                starship.getFilms().add(x);
                            }

                            data.getStarships().save(starship);
                        }

                    }
                    for(SpeciesDto speciesDto : speciesDtos){
                        var existingSpecies = data.getSpecies().findSpeciesByName(speciesDto.getName());
                        if(existingSpecies.isPresent()){
                            existingSpecies.get().getPeople().add(existingPerson.get());
                            existingPerson.get().getSpecies().add(existingSpecies.get());

                            if(!x.getSpecies().contains(existingSpecies.get())){
                                x.getSpecies().add(existingSpecies.get());
                                existingSpecies.get().getFilms().add(x);
                            }

                            data.getSpecies().save(existingSpecies.get());
                        } else {
                            var species = entityMapper.forSpecies().map(speciesDto);
                            species.getPeople().add(existingPerson.get());
                            existingPerson.get().getSpecies().add(species);

                            if(!x.getSpecies().contains(species)){
                                x.getSpecies().add(species);
                                species.getFilms().add(x);
                            }

                            data.getSpecies().save(species);
                        }
                    }

                    //                        x.getCharacters().add(existingPerson.get());
                    existingPerson.get().getFilms().add(x);

                    data.getFilms().save(x);
                    data.getPeople().save(existingPerson.get());
                }
                else {
                    var person = entityMapper.forPerson().map(personDto);
                    data.getPeople().save(person);
                    for(VehicleDto vehicleDto : vehicleDtos){
                        var existingVehicle = data.getVehicles().findVehicleByName(vehicleDto.getName());
                        if(existingVehicle.isPresent()){
                            existingVehicle.get().getPilots().add(person);
                            person.getVehicles().add(existingVehicle.get());

                            if(!x.getVehicles().contains(existingVehicle.get())){
                                x.getVehicles().add(existingVehicle.get());
                                existingVehicle.get().getFilms().add(x);
                            }

                            data.getVehicles().save(existingVehicle.get());
                        } else {
                            var vehicle = entityMapper.forVehicle().map(vehicleDto);
                            vehicle.getPilots().add(person);
                            person.getVehicles().add(vehicle);

                            if(!x.getVehicles().contains(vehicle)){
                                x.getVehicles().add(vehicle);
                                vehicle.getFilms().add(x);
                            }

                            data.getVehicles().save(vehicle);
                        }
                    }
                    for(StarshipDto starshipDto : starshipsDtos){
                        var existingStarship = data.getStarships().findStarshipByName(starshipDto.getName());
                        if(existingStarship.isPresent()){
                            existingStarship.get().getPilots().add(person);
                            person.getStarships().add(existingStarship.get());

                            if(!x.getStarships().contains(existingStarship.get())){
                                x.getStarships().add(existingStarship.get());
                                existingStarship.get().getFilms().add(x);
                            }

                            data.getStarships().save(existingStarship.get());
                        } else {
                            var starship = entityMapper.forStarship().map(starshipDto);
                            starship.getPilots().add(person);
                            person.getStarships().add(starship);

                            if(!x.getStarships().contains(starship)){
                                x.getStarships().add(starship);
                                starship.getFilms().add(x);
                            }

                            data.getStarships().save(starship);
                        }
                    }
                    for(SpeciesDto speciesDto : speciesDtos){
                        var existingSpecies = data.getSpecies().findSpeciesByName(speciesDto.getName());
                        if(existingSpecies.isPresent()){
                            existingSpecies.get().getPeople().add(person);
                            person.getSpecies().add(existingSpecies.get());

                            if(!x.getSpecies().contains(existingSpecies.get())){
                                x.getSpecies().add(existingSpecies.get());
                                existingSpecies.get().getFilms().add(x);
                            }

                            data.getSpecies().save(existingSpecies.get());
                        } else {
                            var species = entityMapper.forSpecies().map(speciesDto);
                            species.getPeople().add(person);
                            person.getSpecies().add(species);

                            if(!x.getSpecies().contains(species)){
                                x.getSpecies().add(species);
                                species.getFilms().add(x);
                            }

                            data.getSpecies().save(species);
                        }

                    }

                    //                        x.getCharacters().add(person);
                    person.getFilms().add(x);
                    data.getFilms().save(x);
                    data.getPeople().save(person);
                }
            });

            data.getFilms().save(x);

        });
    }
}