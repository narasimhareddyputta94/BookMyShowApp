package com.bookmyshow.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel {

    private String name;
    private String address;

    @OneToMany(mappedBy = "theatre")
    private List<Screen> screens;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;
}
