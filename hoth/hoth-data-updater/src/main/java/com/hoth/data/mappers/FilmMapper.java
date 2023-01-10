package com.hoth.data.mappers;

import com.hoth.client.contract.FilmDto;
import com.hoth.data.model.Film;
import org.springframework.stereotype.Component;

@Component
public class FilmMapper implements IMapEntities<FilmDto, Film> {
    @Override
    public Film map(FilmDto filmDto) {
        return map(filmDto, new Film());
    }

    @Override
    public Film map(FilmDto filmDto, Film film) {
        film.setTitle(filmDto.getTitle());
        film.setEpisodeId(filmDto.getEpisodeId());
        film.setDirector(filmDto.getDirector());
        film.setProducer(filmDto.getProducer());
        film.setReleaseDate(filmDto.getReleaseDate());
        return film;
    }
}
