package com.hoth.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Film> films = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Person> people = new HashSet<>();


}
