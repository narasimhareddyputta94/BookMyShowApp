package com.bookmyshow.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel {

    private String name;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

    @OneToMany(mappedBy = "screen")
    private List<Show> shows;

    @OneToMany(mappedBy = "screen")
    private List<Seat> seats;

    private int ScreenNumber;

}
