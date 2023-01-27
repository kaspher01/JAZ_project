package com.hoth.webapi.services;

import com.hoth.data.repositories.ICatalogData;
import com.hoth.webapi.contract.PersonDto;
import com.hoth.webapi.services.utilis.ICatalogDtoMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PersonService implements IPersonService{
    private final ICatalogData db;
    private final ICatalogDtoMappers mapper;

    @Autowired
    public PersonService(ICatalogData db, ICatalogDtoMappers mapper) {
        this.db = db;
        this.mapper = mapper;
    }

    @Override
    public PersonDto getPersonById(int id) {
        var optionalPerson = db.getPeople().findPersonById(id);
        return optionalPerson.map(person -> mapper.forCharacters().map(person)).orElseThrow();
    }
}
