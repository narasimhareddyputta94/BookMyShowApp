package com.bookmyshow.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter


public class Screen extends BaseModel{

    private String name;

    /*
    screen seat
    1       M
    1       1

    */
    @OneToMany
    private List<Seat> seats;
    /*
    screen seat
    1       M
    1       1

     */
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;

}
