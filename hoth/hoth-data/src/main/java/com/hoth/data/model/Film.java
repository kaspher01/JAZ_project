package com.hoth.data.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private int episodeId;
    private String director;
    private String producer;
    private Date releaseDate;

    @ManyToMany(mappedBy = "films")
    private Set<Person> characters = new HashSet<>();

    @ManyToMany(mappedBy = "films")
    private Set<Species> species = new HashSet<>();

    @ManyToMany(mappedBy = "films")
    private Set<Starship> starships = new HashSet<>();

    @ManyToMany(mappedBy = "films")
    private Set<Vehicle> vehicles = new HashSet<>();

    @ManyToMany(mappedBy = "films")
    private Set<Planet> planets = new HashSet<>();
}
