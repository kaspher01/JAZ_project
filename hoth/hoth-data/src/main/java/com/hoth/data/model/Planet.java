package com.hoth.data.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String diameter;
    private String rotationPeriod;
    private String orbitalPeriod;
    private String gravity;
    private String population;
    private String climate;
    private String terrain;
    private String surfaceWater;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Film> films = new HashSet<>();

    @OneToMany(mappedBy = "homeworld", fetch = FetchType.EAGER)
    private Set<Person> residents = new HashSet<>();



}
