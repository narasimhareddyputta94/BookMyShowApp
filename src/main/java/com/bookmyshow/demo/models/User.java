package com.bookmyshow.demo.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter


public class User extends BaseModel{

    private String name;
    private String email;
    private String phoneNumber;
    private List<Booking> bookings;

}
