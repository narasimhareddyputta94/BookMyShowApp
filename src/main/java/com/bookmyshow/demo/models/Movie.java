package com.bookmyshow.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Movie extends BaseModel {

    private String name;
    private String language;
    private String genre;

    @OneToMany(mappedBy = "movie")
    private List<Show> shows;
}
