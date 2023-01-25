package com.hoth.data.repositories;

import com.hoth.data.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SpeciesRepository extends JpaRepository<Species, Long> {
    @Query("select s from Species s where s.name=:name")
    Optional<Species> findSpeciesByName(String name);
}
