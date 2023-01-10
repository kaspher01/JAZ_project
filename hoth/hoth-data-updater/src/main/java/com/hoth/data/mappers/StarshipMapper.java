package com.hoth.data.mappers;

import com.hoth.client.contract.StarshipDto;
import com.hoth.data.model.Starship;
import org.springframework.stereotype.Component;

@Component
public class StarshipMapper implements IMapEntities<StarshipDto, Starship> {
    @Override
    public Starship map(StarshipDto starshipDto) {
        return map(starshipDto, new Starship());
    }

    @Override
    public Starship map(StarshipDto starshipDto, Starship starship) {
        starship.setName(starshipDto.getName());
        starship.setModel(starshipDto.getModel());
        starship.setStarshipClass(starshipDto.getStarshipClass());
        starship.setManufacturer(starshipDto.getManufacturer());
        starship.setCostInCredits(starshipDto.getCostInCredits());
        starship.setLength(starshipDto.getLength());
        starship.setCrew(starshipDto.getCrew());
        starship.setPassengers(starshipDto.getPassengers());
        starship.setMaxAtmospheringSpeed(starshipDto.getMaxAtmospheringSpeed());
        starship.setMaxMegalights(starshipDto.getMaxMegalights());
        return starship;
    }
}
