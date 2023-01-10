package com.hoth.data.mappers;

import com.hoth.client.contract.PlanetDto;
import com.hoth.data.model.Planet;
import org.springframework.stereotype.Component;

@Component
public class PlanetMapper implements IMapEntities<PlanetDto, Planet>{
    @Override
    public Planet map(PlanetDto planetDto) {
        return map(planetDto, new Planet());
    }

    @Override
    public Planet map(PlanetDto planetDto, Planet planet) {
        planet.setName(planetDto.getName());
        planet.setDiameter(planetDto.getDiameter());
        planet.setRotationPeriod(planetDto.getRotationPeriod());
        planet.setOrbitalPeriod(planetDto.getOrbitalPeriod());
        planet.setGravity(planetDto.getGravity());
        planet.setPopulation(planetDto.getPopulation());
        planet.setClimate(planetDto.getClimate());
        planet.setTerrain(planetDto.getTerrain());
        planet.setSurfaceWater(planetDto.getSurfaceWater());
        return planet;
    }
}
