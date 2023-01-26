package com.hoth.webapi.services.utilis;

import com.hoth.webapi.contract.StarshipDto;
import com.hoth.data.model.Starship;
import org.springframework.stereotype.Component;

@Component
public class StarshipDtoMapper implements IMapDtos<Starship, StarshipDto> {
    @Override
    public StarshipDto map(Starship starship) {
        return map(starship, new StarshipDto());
    }

    @Override
    public StarshipDto map(Starship starship, StarshipDto starshipDto) {
        starshipDto.setName(starship.getName());
        starshipDto.setModel(starship.getModel());
        starshipDto.setStarshipClass(starship.getStarshipClass());
        starshipDto.setManufacturer(starship.getManufacturer());
        starshipDto.setCostInCredits(starship.getCostInCredits());
        starshipDto.setLength(starship.getLength());
        starshipDto.setCrew(starship.getCrew());
        starshipDto.setPassengers(starship.getPassengers());
        starshipDto.setMaxAtmospheringSpeed(starship.getMaxAtmospheringSpeed());
        starshipDto.setMaxMegalights(starship.getMaxMegalights());
        return starshipDto;
    }
}
