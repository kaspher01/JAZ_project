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

    @ManyToMany
    private Set<Film> films = new HashSet<>();

    @ManyToMany
    private Set<Person> residents = new HashSet<>();



}
