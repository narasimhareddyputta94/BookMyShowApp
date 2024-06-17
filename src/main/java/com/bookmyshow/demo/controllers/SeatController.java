package com.bookmyshow.demo.controllers;

import com.bookmyshow.demo.models.Seat;
import com.bookmyshow.demo.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seats")
public class SeatController {
    @Autowired
    private SeatRepository seatRepository;

    @GetMapping
    public ResponseEntity<List<Seat>> getAllSeats() {
        return new ResponseEntity<>(seatRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSeatById(@PathVariable Long id) {
        Optional<Seat> seat = seatRepository.findById(id);
        if (seat.isPresent()) {
            return new ResponseEntity<>(seat.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Seat not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Seat> createSeat(@RequestBody Seat seat) {
        Seat savedSeat = seatRepository.save(seat);
        return new ResponseEntity<>(savedSeat, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSeat(@PathVariable Long id, @RequestBody Seat seatDetails) {
        Optional<Seat> seatOptional = seatRepository.findById(id);
        if (!seatOptional.isPresent()) {
            return new ResponseEntity<>("Seat not found", HttpStatus.NOT_FOUND);
        }
        Seat seat = seatOptional.get();
        seat.setSeatNumber(seatDetails.getSeatNumber());
        seat.setSeatNumber(seatDetails.getSeatType());
        seatRepository.save(seat);
        return new ResponseEntity<>(seat, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSeat(@PathVariable Long id) {
        if (!seatRepository.existsById(id)) {
            return new ResponseEntity<>("Seat not found", HttpStatus.NOT_FOUND);
        }
        seatRepository.deleteById(id);
        return new ResponseEntity<>("Seat deleted successfully", HttpStatus.OK);
    }
}
