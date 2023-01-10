package com.hoth.data.mappers;

import com.hoth.client.contract.SpeciesDto;
import com.hoth.data.model.Species;
import org.springframework.stereotype.Component;

@Component
public class SpeciesMapper implements IMapEntities<SpeciesDto, Species> {
    @Override
    public Species map(SpeciesDto speciesDto) {
        return map(speciesDto, new Species());
    }

    @Override
    public Species map(SpeciesDto speciesDto, Species species) {
        species.setName(speciesDto.getName());
        species.setClassification(speciesDto.getClassification());
        species.setDesignation(speciesDto.getDesignation());
        species.setAvgHeight(speciesDto.getAvgHeight());
        species.setAvgLifespan(speciesDto.getAvgLifespan());
        species.setEyeColors(speciesDto.getEyeColors());
        species.setSkinColors(speciesDto.getSkinColors());
        species.setLanguage(speciesDto.getLanguage());
        return species;
    }
}
