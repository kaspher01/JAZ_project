package com.hoth.data.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String model;
    private String vehicleClass;
    private String manufacturer;
    private String costInCredits;
    private String length;
    private String crew;
    private String passengers;
    private String maxAtmospheringSpeed;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Film> films = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Person> pilots = new HashSet<>();
}
