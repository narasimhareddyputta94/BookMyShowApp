package com.bookmyshow.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "`show`")
public class Show extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

    private Date startTime;
    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShowSeatType> showSeatTypes;
}
