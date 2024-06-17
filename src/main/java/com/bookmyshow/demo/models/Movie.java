package com.bookmyshow.demo.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Movie extends BaseModel {
    private String title;
    private String genre;
    private String duration;
    private String language;
    private String releaseDate;
    private String director;
    private String cast;
    private String description;
    private String posterUrl;
    private String trailerUrl;
    private String status;
}
