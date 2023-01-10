package com.hoth.data.mappers;

import com.hoth.client.contract.PersonDto;
import com.hoth.data.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper implements IMapEntities<PersonDto, Person> {
    @Override
    public Person map(PersonDto personDto) {
        return map(personDto, new Person());
    }

    @Override
    public Person map(PersonDto personDto, Person person) {
        person.setName(personDto.getName());
        person.setBirthYear(personDto.getBirthYear());
        person.setGender(personDto.getGender());
        person.setHeight(personDto.getHeight());
        person.setMass(personDto.getMass());
        person.setSkinColor(personDto.getSkinColor());
        return person;
    }
}
