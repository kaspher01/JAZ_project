package com.hoth.webapi.controllers;

import com.hoth.webapi.contract.FilmDto;
import com.hoth.webapi.services.IFilmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/films")
@Log4j2
@RequiredArgsConstructor
public class FilmController {

    private final IFilmService filmService;

    @GetMapping("/all")
    public List<FilmDto> getAll(){
        log.warn("Exposing all data.");
        return filmService.getAll();
    }
}
