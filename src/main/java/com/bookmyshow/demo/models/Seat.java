package com.bookmyshow.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seat extends BaseModel {

    private String seatNumber;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;
}
