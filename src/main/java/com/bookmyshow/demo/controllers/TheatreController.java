package com.bookmyshow.demo.controllers;

import com.bookmyshow.demo.models.Theatre;
import com.bookmyshow.demo.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/theatres")
public class TheatreController {
    @Autowired
    private TheatreRepository theatreRepository;

    @GetMapping
    public ResponseEntity<List<Theatre>> getAllTheatres() {
        return new ResponseEntity<>(theatreRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTheatreById(@PathVariable Long id) {
        Optional<Theatre> theatre = theatreRepository.findById(id);
        if (theatre.isPresent()) {
            return new ResponseEntity<>(theatre.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Theatre not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Theatre> createTheatre(@RequestBody Theatre theatre) {
        Theatre savedTheatre = theatreRepository.save(theatre);
        return new ResponseEntity<>(savedTheatre, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTheatre(@PathVariable Long id, @RequestBody Theatre theatreDetails) {
        Optional<Theatre> theatreOptional = theatreRepository.findById(id);
        if (!theatreOptional.isPresent()) {
            return new ResponseEntity<>("Theatre not found", HttpStatus.NOT_FOUND);
        }
        Theatre theatre = theatreOptional.get();
        theatre.setName(theatreDetails.getName());
        theatre.setScreens(theatreDetails.getScreens());
        theatreRepository.save(theatre);
        return new ResponseEntity<>(theatre, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTheatre(@PathVariable Long id) {
        if (!theatreRepository.existsById(id)) {
            return new ResponseEntity<>("Theatre not found", HttpStatus.NOT_FOUND);
        }
        theatreRepository.deleteById(id);
        return new ResponseEntity<>("Theatre deleted successfully", HttpStatus.OK);
    }
}