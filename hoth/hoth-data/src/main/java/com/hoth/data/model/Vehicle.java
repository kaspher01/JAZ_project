package com.hoth.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Vehicle {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String model;
    private String vehicleClass;
    private String manufacturer;
    private String costInCredits;
    private String length;
    private String crew;
    private String passengers;
    private String maxAtmospheringSpeed;

    @ManyToMany(mappedBy = "starship", cascade = CascadeType.ALL)
    private List<Film> films = new ArrayList<>();

    @ManyToMany(mappedBy = "starship", cascade = CascadeType.ALL)
    private List<Person> pilots = new ArrayList<>();
}
