package com.hoth.data.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String birthYear;
    private String gender;
    private String height;
    private String mass;
    private String skinColor;

    @ManyToOne
    private Planet homeworld;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Film> films = new HashSet<>();

    @ManyToMany(mappedBy = "people", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Species> species = new HashSet<>();

    @ManyToMany(mappedBy = "pilots", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Starship> starships = new HashSet<>();

    @ManyToMany(mappedBy = "pilots", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Vehicle> vehicles = new HashSet<>();



}
