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


            //Lists of dtos that are in a film
            List<PersonDto> peopleDto = new ArrayList<>();
            List<StarshipDto> starshipsDto = new ArrayList<>();
            List<VehicleDto> vehiclesDtos = new ArrayList<>();
            List<SpeciesDto> speciesDtos = new ArrayList<>();
            List<PlanetDto> planetsDtos = new ArrayList<>();


            //Operations on PeopleDtos
            filmDto.getCharactersUrls()
                    .forEach(l -> peopleDto.add(
                            client.getPerson(Integer.parseInt(l.replaceFirst(".*/([^/?]+).*", "$1")))
                    ));

            var peopleNames = data.getPeople()
                    .findAll()
                    .stream()
                    .map(Person::getName)
                    .toList();

            var peopleToSave = peopleDto
                    .stream()
                    .filter(Predicate.not(p -> peopleNames.contains(p.getName())))
                    .map(p -> entityMapper.forPerson().map(p))
                    .toList();


            //Operations on StarshipsDtos
            filmDto.getStarshipsUrls()
                    .forEach(l -> starshipsDto.add(
                            client.getStarship(Integer.parseInt(l.replaceFirst(".*/([^/?]+).*", "$1"))))
                    );

            var starshipsNames = data.getStarships()
                    .findAll()
                    .stream()
                    .map(Starship::getName)
                    .toList();

            var starshipsToSave = starshipsDto
                    .stream()
                    .filter(Predicate.not(s -> starshipsNames.contains(s.getName())))
                    .map(s -> entityMapper.forStarship().map(s))
                    .toList();

            //Operations on VehiclesDtos
            filmDto.getVehiclesUrls()
                    .forEach(l -> vehiclesDtos.add(
                            client.getVehicle(Integer.parseInt(l.replaceFirst(".*/([^/?]+).*", "$1"))))
                    );

            var vehilcesNames = data.getVehicles()
                    .findAll()
                    .stream()
                    .map(Vehicle::getName)
                    .toList();

            var vehiclesToSave = vehiclesDtos
                    .stream()
                    .filter(Predicate.not(v -> vehilcesNames.contains(v.getName())))
                    .map(v -> entityMapper.forVehicle().map(v))
                    .toList();
            //Operations on SpeciesDtos
            filmDto.getSpeciesUrls()
                    .forEach(l -> speciesDtos.add(
                            client.getSpecies(Integer.parseInt(l.replaceFirst(".*/([^/?]+).*", "$1"))))
                    );

            var speciesNames = data.getSpecies()
                    .findAll()
                    .stream()
                    .map(Species::getName)
                    .toList();

            var speciesToSave = speciesDtos
                    .stream()
                    .filter(Predicate.not(s -> speciesNames.contains(s.getName())))
                    .map(s -> entityMapper.forSpecies().map(s))
                    .toList();

            //Operations on PlanetsDtos
            filmDto.getPlanetsUrls()
                    .forEach(l -> planetsDtos.add(
                            client.getPlanet(Integer.parseInt(l.replaceFirst(".*/([^/?]+).*", "$1"))))
                    );

            var planetsNames = data.getPlanets()
                    .findAll()
                    .stream()
                    .map(Planet::getName)
                    .toList();

            var planetsToSave = planetsDtos
                    .stream()
                    .filter(Predicate.not(p -> planetsNames.contains(p.getName())))
                    .map(p -> entityMapper.forPlanet().map(p))
                    .toList();


            //Saving entities to database

            data.getPeople().saveAll(peopleToSave);
            data.getStarships().saveAll(starshipsToSave);
            data.getVehicles().saveAll(vehiclesToSave);
            data.getSpecies().saveAll(speciesToSave);
            data.getPlanets().saveAll(planetsToSave);

            //Setting relations between entities

            peopleDto.stream().map(p -> {
                List<SpeciesDto> personSpeciesDtos = new ArrayList<>();
                List<StarshipDto> personStarshipsDto = new ArrayList<>();
                List<VehicleDto> personVehiclesDto = new ArrayList<>();
                PlanetDto personHomeworldDto = client.getPlanet(Integer.parseInt(p.getHomeworldUrl().replaceFirst(".*/([^/?]+).*", "$1")));

                List<Species> personSpecies = new ArrayList<>();
                List<Starship> personStarships = new ArrayList<>();
                List<Vehicle> personVehicles = new ArrayList<>();
                Planet personHomeworld;

                p.getSpeciesUrls()
                        .forEach(l -> personSpeciesDtos.add(
                                client.getSpecies(Integer.parseInt(l.replaceFirst(".*/([^/?]+).*", "$1"))))
                        );

                p.getStarshipsUrls()
                        .forEach(l -> personStarshipsDto.add(
                                client.getStarship(Integer.parseInt(l.replaceFirst(".*/([^/?]+).*", "$1"))))
                        );

                p.getVehiclesUrls()
                        .forEach(l -> personVehiclesDto.add(
                                client.getVehicle(Integer.parseInt(l.replaceFirst(".*/([^/?]+).*", "$1"))))
                        );

                for (SpeciesDto speciesDto : personSpeciesDtos) {
                    personSpecies.add(entityMapper.forSpecies().map(speciesDto));
                }

                for (VehicleDto vehicleDto : personVehiclesDto){
                    personVehicles.add(entityMapper.forVehicle().map(vehicleDto));
                }

                for (StarshipDto starshipDto : personStarshipsDto){
                    personStarships.add(entityMapper.forStarship().map(starshipDto));
                }

                personHomeworld = entityMapper.forPlanet().map(personHomeworldDto);

                var person = entityMapper.forPerson().map(p);

                //Person - species
                person.getSpecies().addAll(personSpecies);
                personSpecies.forEach(s->s.getPeople().add(person));
                //Person - film
                person.getFilms().add(x);
                x.getCharacters().add(person);
                //Person - starship
                person.getStarships().addAll(personStarships);
                personStarships.forEach(s->s.getPilots().add(person));
                //Person - vehicle
                person.getVehicles().addAll(personVehicles);
                personVehicles.forEach(v->v.getPilots().add(person));
                //Person - planet
                person.setHomeworld(personHomeworld);
                personHomeworld.getResidents().add(person);


                data.getFilms().save(x);
                data.getStarships().saveAll(personStarships);
                data.getVehicles().saveAll(personVehicles);
                data.getSpecies().saveAll(personSpecies);
                data.getPlanets().save(personHomeworld);
                return person;
            }).forEach(p->data.getPeople().save(p));



            //Film - starship
            for (Starship starship : data.getStarships().findAll()) {
                x.getStarships().add(starship);
                starship.getFilms().add(x);
            }

            //Film - vehicle
            for (Vehicle vehicle : data.getVehicles().findAll()) {
                x.getVehicles().add(vehicle);
                vehicle.getFilms().add(x);
            }

            //Film - species
            for (Species species : data.getSpecies().findAll()) {
                x.getSpecies().add(species);
                species.getFilms().add(x);
            }

            //Film - planet
            for (Planet planet : data.getPlanets().findAll()) {
                x.getPlanets().add(planet);
                planet.getFilms().add(x);
            }

            data.getFilms().save(x);

        });
    }
}