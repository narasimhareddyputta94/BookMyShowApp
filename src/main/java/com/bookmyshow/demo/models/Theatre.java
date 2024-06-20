package com.bookmyshow.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Theatre extends BaseModel {
    private String name;
    private String address;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Screen> screens;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Show> shows;
}
