package com.hoth.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(cascade = CascadeType.ALL)
    private Planet homeworld;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Film> films = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Species> species = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Starship> starships = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Vehicle> vehicles = new ArrayList<>();

}
