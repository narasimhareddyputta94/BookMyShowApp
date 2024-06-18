package com.bookmyshow.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    private double amount;

    private LocalDateTime lockTime;

    private boolean locked;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Payment> payments;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<ShowSeat> showSeats;  // Ensure this mapping is correct
}