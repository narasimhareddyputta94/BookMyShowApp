package com.bookmyshow.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter


public class ShowSeat extends BaseModel{

    /*
    showseat show
    1           1
    M           1
     */
    @ManyToOne
    private Show show;

    /*
    1       1
    M       1
     */

    @ManyToOne
    private Seat seat;

    @Enumerated(EnumType.ORDINAL)
    private SeatStatus seatStatus;

}
