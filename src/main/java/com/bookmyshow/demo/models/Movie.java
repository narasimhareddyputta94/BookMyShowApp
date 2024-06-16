package com.bookmyshow.demo.models;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter

public class Movie extends BaseModel{

    private String name;
    private String language;
    private String genre;


}
