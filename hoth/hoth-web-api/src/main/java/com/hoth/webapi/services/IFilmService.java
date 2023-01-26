package com.hoth.webapi.services;

import com.hoth.webapi.contract.FilmDto;

import java.util.List;

public interface IFilmService {
    List<FilmDto> getAll();
}
