package com.bookmyshow.demo.controllers;

import com.bookmyshow.demo.models.Screen;
import com.bookmyshow.demo.repositories.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/screens")
public class ScreenController {
    @Autowired
    private ScreenRepository screenRepository;

    @GetMapping
    public ResponseEntity<List<Screen>> getAllScreens() {
        return new ResponseEntity<>(screenRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getScreenById(@PathVariable Long id) {
        Optional<Screen> screen = screenRepository.findById(id);
        if (screen.isPresent()) {
            return new ResponseEntity<>(screen.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Screen not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Screen> createScreen(@RequestBody Screen screen) {
        Screen savedScreen = screenRepository.save(screen);
        return new ResponseEntity<>(savedScreen, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateScreen(@PathVariable Long id, @RequestBody Screen screenDetails) {
        Optional<Screen> screenOptional = screenRepository.findById(id);
        if (!screenOptional.isPresent()) {
            return new ResponseEntity<>("Screen not found", HttpStatus.NOT_FOUND);
        }
        Screen screen = screenOptional.get();
        screen.setName(screenDetails.getName());
        screen.setSeats(screenDetails.getSeats());
        screen.setShows(screenDetails.getShows());
        screenRepository.save(screen);
        return new ResponseEntity<>(screen, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteScreen(@PathVariable Long id) {
        if (!screenRepository.existsById(id)) {
            return new ResponseEntity<>("Screen not found", HttpStatus.NOT_FOUND);
        }
        screenRepository.deleteById(id);
        return new ResponseEntity<>("Screen deleted successfully", HttpStatus.OK);
    }
}