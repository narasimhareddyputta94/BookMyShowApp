package com.bookmyshow.demo.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity

public class Show extends BaseModel{

    private Date staeTime;
    private Date endTime;
    private Movie movie;
    private Screen screen;
}
