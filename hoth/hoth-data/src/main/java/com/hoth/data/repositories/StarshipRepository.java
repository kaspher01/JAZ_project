package com.hoth.data.repositories;

import com.hoth.data.model.Starship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StarshipRepository extends JpaRepository<Starship, Long> {
    @Query("select s from Starship s where s.name=:name")
    Optional<Starship> findStarshipByName(String name);
}
