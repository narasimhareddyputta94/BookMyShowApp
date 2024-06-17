package com.bookmyshow.demo.controllers;

import com.bookmyshow.demo.models.Show;
import com.bookmyshow.demo.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shows")
public class ShowController {
    @Autowired
    private ShowRepository showRepository;

    @GetMapping
    public ResponseEntity<List<Show>> getAllShows() {
        return new ResponseEntity<>(showRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getShowById(@PathVariable Long id) {
        Optional<Show> show = showRepository.findById(id);
        if (show.isPresent()) {
            return new ResponseEntity<>(show.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Show not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Show> createShow(@RequestBody Show show) {
        Show savedShow = showRepository.save(show);
        return new ResponseEntity<>(savedShow, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateShow(@PathVariable Long id, @RequestBody Show showDetails) {
        Optional<Show> showOptional = showRepository.findById(id);
        if (!showOptional.isPresent()) {
            return new ResponseEntity<>("Show not found", HttpStatus.NOT_FOUND);
        }
        Show show = showOptional.get();
        show.setStartTime(showDetails.getStartTime());
        show.setEndTime(showDetails.getEndTime());
        show.setMovie(showDetails.getMovie());
        show.setScreen(showDetails.getScreen());
        show.setShowSeatTypes(showDetails.getShowSeatTypes());
        showRepository.save(show);
        return new ResponseEntity<>(show, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteShow(@PathVariable Long id) {
        if (!showRepository.existsById(id)) {
            return new ResponseEntity<>("Show not found", HttpStatus.NOT_FOUND);
        }
        showRepository.deleteById(id);
        return new ResponseEntity<>("Show deleted successfully", HttpStatus.OK);
    }
}
