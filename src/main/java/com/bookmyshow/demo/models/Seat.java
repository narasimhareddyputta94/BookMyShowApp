package com.bookmyshow.demo.models;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter


public class Seat extends BaseModel{


    private int number;
    private String seatType;
    private int row;
    private int column;

}
