package com.hoth.webapi.services;

import com.hoth.webapi.contract.PersonDto;

public interface IPersonService {
    PersonDto getPersonById(int id);

    void updatePersonById(int id, PersonDto personDto);
}
