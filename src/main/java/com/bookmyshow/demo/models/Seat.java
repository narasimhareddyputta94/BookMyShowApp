package com.bookmyshow.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel {

    private String seatNumber;
    private SeatStatus seatStatus;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }
}
