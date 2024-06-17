package com.bookmyshow.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;

    @OneToMany
    private List<ShowSeat> showSeats;

    private int amount;

    @OneToMany
    private List<Payment> payments;

    @Version
    private Long version;
}
