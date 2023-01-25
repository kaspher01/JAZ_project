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
public class Starship{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String model;
    private String starshipClass;
    private String manufacturer;
    private String costInCredits;
    private String length;
    private String crew;
    private String passengers;
    private String maxAtmospheringSpeed;
    private String maxMegalights;


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Film> films = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Person> pilots = new HashSet<>();


}
