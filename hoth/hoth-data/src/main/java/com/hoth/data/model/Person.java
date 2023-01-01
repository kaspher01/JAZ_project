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
    @GeneratedValue
    private long id;
    private String name;
    private String birth_year;
    private String gender;
    private String height;
    private String mass;
    private String skin_color;

    @ManyToOne
    private Planet homeworld;

    @ManyToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Film> films = new ArrayList<>();

    @ManyToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Species> species = new ArrayList<>();

    @ManyToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Starship> starships = new ArrayList<>();

    @ManyToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles = new ArrayList<>();

}
