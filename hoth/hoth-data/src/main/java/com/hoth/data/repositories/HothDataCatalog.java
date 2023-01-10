package com.hoth.data.repositories;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Getter
public class HothDataCatalog implements ICatalogData{
    private final FilmRepository films;
    private final PersonRepository people;
    private final PlanetRepository planets;
    private final SpeciesRepository species;
    private final StarshipRepository starships;
    private final VehicleRepository vehicles;
}
