package com.hoth.data.repositories;

import com.hoth.data.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlanetRepository extends JpaRepository<Planet, Long> {
    @Query("select p from Planet p where p.name=:name")
    Optional<Planet> findPlanetByName(String name);
}
