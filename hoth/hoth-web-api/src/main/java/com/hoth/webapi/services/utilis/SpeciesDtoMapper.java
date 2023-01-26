package com.hoth.webapi.services.utilis;

import com.hoth.client.contract.SpeciesDto;
import com.hoth.data.model.Species;
import org.springframework.stereotype.Component;

@Component
public class SpeciesDtoMapper implements IMapDtos<Species, SpeciesDto> {
    @Override
    public SpeciesDto map(Species species) {
        return map(species, new SpeciesDto());
    }

    @Override
    public SpeciesDto map(Species species, SpeciesDto speciesDto) {
        speciesDto.setName(species.getName());
        speciesDto.setClassification(species.getClassification());
        speciesDto.setDesignation(species.getDesignation());
        speciesDto.setAvgHeight(species.getAvgHeight());
        speciesDto.setAvgLifespan(species.getAvgLifespan());
        speciesDto.setEyeColors(species.getEyeColors());
        speciesDto.setSkinColors(species.getSkinColors());
        speciesDto.setLanguage(species.getLanguage());
        return speciesDto;
    }

}
