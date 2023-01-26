package com.hoth.webapi.controllers;

import com.hoth.webapi.contract.FilmDto;
import com.hoth.webapi.services.IFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("films/")

public class FilmController {

    private final IFilmService filmService;

    @Autowired
    public FilmController(IFilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping()
    public List<FilmDto> getAllFilms(){
        return filmService.getAll();
    }
}
