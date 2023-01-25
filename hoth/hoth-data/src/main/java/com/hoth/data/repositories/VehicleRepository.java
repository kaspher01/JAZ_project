package com.hoth.data.repositories;

import com.hoth.data.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("select v from Vehicle v where v.name=:name")
    Optional<Vehicle> findVehicleByName(String name);

}
