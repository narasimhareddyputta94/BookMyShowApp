package com.bookmyshow.demo.controllers;

import com.bookmyshow.demo.models.ShowSeat;
import com.bookmyshow.demo.repositories.ShowSeatRepositor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/showSeats")
public class ShowSeatController {
    @Autowired
    private ShowSeatRepositor showSeatRepository;

    @GetMapping
    public ResponseEntity<List<ShowSeat>> getAllShowSeats() {
        return new ResponseEntity<>(showSeatRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getShowSeatById(@PathVariable Long id) {
        Optional<ShowSeat> showSeat = showSeatRepository.findById(id);
        if (showSeat.isPresent()) {
            return new ResponseEntity<>(showSeat.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ShowSeat not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ShowSeat> createShowSeat(@RequestBody ShowSeat showSeat) {
        ShowSeat savedShowSeat = showSeatRepository.save(showSeat);
        return new ResponseEntity<>(savedShowSeat, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateShowSeat(@PathVariable Long id, @RequestBody ShowSeat showSeatDetails) {
        Optional<ShowSeat> showSeatOptional = showSeatRepository.findById(id);
        if (!showSeatOptional.isPresent()) {
            return new ResponseEntity<>("ShowSeat not found", HttpStatus.NOT_FOUND);
        }
        ShowSeat showSeat = showSeatOptional.get();
        showSeat.setSeat(showSeatDetails.getSeat());
        showSeat.setSeatStatus(showSeatDetails.getSeatStatus());
        showSeatRepository.save(showSeat);
        return new ResponseEntity<>(showSeat, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteShowSeat(@PathVariable Long id) {
        if (!showSeatRepository.existsById(id)) {
            return new ResponseEntity<>("ShowSeat not found", HttpStatus.NOT_FOUND);
        }
        showSeatRepository.deleteById(id);
        return new ResponseEntity<>("ShowSeat deleted successfully", HttpStatus.OK);
    }
}
