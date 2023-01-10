package com.hoth.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String classification;
    private String designation;
    private String avgHeight;
    private String avgLifespan;
    private String eyeColors;
    private String skinColors;
    private String language;

    @ManyToOne
    private Planet homeworld;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Person> people = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Film> films = new ArrayList<>();

}
