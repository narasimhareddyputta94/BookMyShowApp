package com.bookmyshow.demo.models;

import jakarta.persistence.*;
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
}
