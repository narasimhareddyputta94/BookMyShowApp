package com.bookmyshow.demo.repositories;

import com.bookmyshow.demo.models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
}
