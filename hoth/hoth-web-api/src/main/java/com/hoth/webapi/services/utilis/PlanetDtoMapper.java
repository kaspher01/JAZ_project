package com.hoth.webapi.services.utilis;

import com.hoth.webapi.contract.PlanetDto;
import com.hoth.data.model.Planet;
import org.springframework.stereotype.Component;

@Component
public class PlanetDtoMapper implements IMapDtos<Planet, PlanetDto> {
    @Override
    public PlanetDto map(Planet planet) {
        return map(planet, new PlanetDto());
    }

    @Override
    public PlanetDto map(Planet planet, PlanetDto planetDto) {
        planetDto.setName(planet.getName());
        planetDto.setDiameter(planet.getDiameter());
        planetDto.setRotationPeriod(planet.getRotationPeriod());
        planetDto.setOrbitalPeriod(planet.getOrbitalPeriod());
        planetDto.setGravity(planet.getGravity());
        planetDto.setPopulation(planet.getPopulation());
        planetDto.setClimate(planet.getClimate());
        planetDto.setTerrain(planet.getTerrain());
        planetDto.setSurfaceWater(planet.getSurfaceWater());
        return planetDto;
    }
}
