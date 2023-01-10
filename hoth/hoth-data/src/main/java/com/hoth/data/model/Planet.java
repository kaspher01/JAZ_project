package com.hoth.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Person> residents = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Film> films = new ArrayList<>();

}
