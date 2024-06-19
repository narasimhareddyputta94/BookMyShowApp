package com.bookmyshow.demo.controllers;

import com.bookmyshow.demo.models.Theatre;
import com.bookmyshow.demo.services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/theatres")
public class TheatreController {

    private final TheatreService theatreService;

    @Autowired
    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @GetMapping
    public ResponseEntity<List<Theatre>> getAllTheatres() {
        List<Theatre> theatres = theatreService.getAllTheatres();
        return new ResponseEntity<>(theatres, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTheatreById(@PathVariable Long id) {
        Optional<Theatre> theatre = theatreService.getTheatreById(id);
        if (theatre.isPresent()) {
            return new ResponseEntity<>(theatre.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Theatre not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Theatre> createTheatre(@RequestBody Theatre theatre) {
        Theatre savedTheatre = theatreService.createTheatre(theatre);
        return new ResponseEntity<>(savedTheatre, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTheatre(@PathVariable Long id, @RequestBody Theatre theatreDetails) {
        try {
            Theatre updatedTheatre = theatreService.updateTheatre(id, theatreDetails);
            return new ResponseEntity<>(updatedTheatre, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTheatre(@PathVariable Long id) {
        try {
            theatreService.deleteTheatre(id);
            return new ResponseEntity<>("Theatre deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
