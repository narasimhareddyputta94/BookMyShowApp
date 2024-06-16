package com.bookmyshow.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ShowSeatType extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    private String seatType;
    private int price;
}
