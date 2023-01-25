package com.hoth.data.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Planet homeworld;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Film> films = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Species> species = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Starship> starships = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Vehicle> vehicles = new HashSet<>();

}
