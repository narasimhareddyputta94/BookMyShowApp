package com.bookmyshow.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;  // Add this property to fix the mapping issue
}