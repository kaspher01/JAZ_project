package com.hoth.webapi.services;

import com.hoth.data.model.Person;
import com.hoth.data.repositories.ICatalogData;
import com.hoth.webapi.contract.PersonDto;
import com.hoth.webapi.services.utilis.ICatalogDtoMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void updatePersonById(int id, PersonDto personDto){
        var person = db.getPeople().findPersonById(id);
        if(person.isPresent()){
            person.get().setName(personDto.getName());
            person.get().setBirthYear(personDto.getBirthYear());
            person.get().setGender(personDto.getGender());
            person.get().setHeight(personDto.getHeight());
            person.get().setMass(personDto.getMass());
            person.get().setSkinColor(personDto.getSkinColor());
            db.getPeople().save(person.get());
        }
    }
}
