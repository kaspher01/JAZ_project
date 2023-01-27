package com.hoth.webapi.controllers;

import com.hoth.webapi.contract.FilmDto;
import com.hoth.webapi.services.IFilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {

    private final IFilmService filmService;

    @GetMapping("/all")
    public List<FilmDto> getAll(){
        return filmService.getAll();
    }
}
