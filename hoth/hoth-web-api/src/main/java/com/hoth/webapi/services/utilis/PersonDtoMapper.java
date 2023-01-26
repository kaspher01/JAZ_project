package com.hoth.webapi.services.utilis;

import com.hoth.webapi.contract.PersonDto;
import com.hoth.data.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonDtoMapper implements IMapDtos<Person, PersonDto> {
    @Override
    public PersonDto map(Person person) {
        return map(person, new PersonDto());
    }

    @Override
    public PersonDto map(Person person, PersonDto personDto) {
        personDto.setName(person.getName());
        personDto.setBirthYear(person.getBirthYear());
        personDto.setGender(person.getGender());
        personDto.setHeight(person.getHeight());
        personDto.setMass(person.getMass());
        personDto.setSkinColor(person.getSkinColor());
        return personDto;
    }
}
