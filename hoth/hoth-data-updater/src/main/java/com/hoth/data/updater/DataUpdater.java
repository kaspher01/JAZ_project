package com.hoth.data.updater;

import com.hoth.client.IStarwarsClient;
import com.hoth.data.mappers.ICatalogMappers;
import com.hoth.data.repositories.ICatalogData;
import org.springframework.stereotype.Component;

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

        entities.forEach(x->{
            data.getFilms().save(x);

        });



    }
}
