package com.hoth.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Starship extends Vehicle{

    @Id
    @GeneratedValue
    private long id;
    private String mglt;


}
