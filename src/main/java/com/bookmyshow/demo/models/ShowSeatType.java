package com.bookmyshow.demo.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter


public class ShowSeatType extends BaseModel{

    private Show show;
    private String seatType;
    private int price;


}
