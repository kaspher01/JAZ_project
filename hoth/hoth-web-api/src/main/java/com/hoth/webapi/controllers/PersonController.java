package com.hoth.webapi.controllers;

import com.hoth.webapi.contract.PersonDto;
import com.hoth.webapi.services.IPersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
@Log4j2
@RequiredArgsConstructor
public class PersonController {
    private final IPersonService personService;

    @GetMapping("/{id}")
    public PersonDto getPersonById(@PathVariable("id") int personId){
        log.warn("Exposing person with id: " + personId);
        return personService.getPersonById(personId);
    }

}
