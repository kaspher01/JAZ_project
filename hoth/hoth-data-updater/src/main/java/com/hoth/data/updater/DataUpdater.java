package com.hoth.data.updater;

import com.hoth.client.IStarwarsClient;
import com.hoth.client.contract.PersonDto;
import com.hoth.data.mappers.ICatalogMappers;
import com.hoth.data.model.Film;
import com.hoth.data.model.Person;
import com.hoth.data.repositories.ICatalogData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

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
                .map(dto->client.getFilm(dto.getId()))
                .toList();

        var entities = films
                .stream()
                .map(dto->entityMapper.forFilm().map(dto))
                .toList();

        var filmsTitles = data.getFilms()
                .findAll()
                .stream()
                .map(Film::getTitle)
                .toList();

        var entitiesToSave = entities
                .stream()
                .filter(Predicate.not(entity->filmsTitles.contains(entity.getTitle())))
                .toList();

        entitiesToSave.forEach(x->{
            data.getFilms().save(x);

            var filmDto = films
                    .stream()
                    .filter(dto->Objects.equals(dto.getTitle(), x.getTitle()))
                    .findFirst()
                    .get();


            List<PersonDto> peopleDto = new ArrayList<>();
            filmDto.getCharactersUrls()
                    .forEach(l->peopleDto.add(client.getPerson(Integer.parseInt(l.replaceFirst(".*/([^/?]+).*", "$1")))));

            var peopleNames = data.getPeople()
                    .findAll()
                    .stream()
                    .map(Person::getName)
                    .toList();

            var peopleToSave = peopleDto
                    .stream()
                    .filter(Predicate.not(p->peopleNames.contains(p.getName())))
                    .map(p->entityMapper.forPerson().map(p))
                    .toList();

            data.getPeople().saveAll(peopleToSave);

            for(Person person : data.getPeople().findAll()){
                x.getCharacters().add(person);
                person.getFilms().add(x);
                data.getFilms().save(x);
            }


        });



    }
}
