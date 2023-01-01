package com.hoth.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Film {

    @Id
    @GeneratedValue
    private long id;

    private String title;
    private int episodeId;
    private String director;
    private String producer;
    private Date releaseDate;

    @ManyToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<Species> species = new ArrayList<>();

    @ManyToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<Starship> starships = new ArrayList<>();

    @ManyToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles = new ArrayList<>();

    @ManyToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<Planet> planets = new ArrayList<>();
}
