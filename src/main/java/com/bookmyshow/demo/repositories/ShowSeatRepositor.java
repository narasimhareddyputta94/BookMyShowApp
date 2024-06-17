package com.bookmyshow.demo.repositories;

import com.bookmyshow.demo.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShowSeatRepositor extends JpaRepository<ShowSeat, Long> {

}
