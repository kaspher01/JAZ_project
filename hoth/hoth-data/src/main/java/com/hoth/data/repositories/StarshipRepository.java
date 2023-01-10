package com.hoth.data.repositories;

import com.hoth.data.model.Starship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarshipRepository extends JpaRepository<Starship, Long> {
}
