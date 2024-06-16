package com.bookmyshow.demo.models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter

public class Theatre extends BaseModel{

    private String name;

    @OneToMany
    private List<Screen> Screens;


}
