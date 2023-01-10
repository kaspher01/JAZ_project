package com.hoth.client;

import com.hoth.client.contract.FilmDto;
import com.hoth.client.contract.PersonDto;
import com.hoth.client.contract.ResultDto;

public interface IStarwarsClient {

    ResultDto getFilms();
    FilmDto getFilm(int id);
    PersonDto getPerson(int id);
}
