package com.bookmyshow.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Region extends BaseModel {
    private String name;

    @OneToMany(mappedBy = "region")
    private List<Theatre> theatres;

    // Method to add a theatre to the region
    public void addTheatre(Theatre theatre) {
        this.theatres.add(theatre);
        theatre.setRegion(this);
    }

    // Method to remove a theatre from the region
    public void removeTheatre(Theatre theatre) {
        this.theatres.remove(theatre);
        theatre.setRegion(null);
    }
}
