package com.bookmyshow.demo.controllers;

import com.bookmyshow.demo.models.ShowSeat;
import com.bookmyshow.demo.services.ShowSeatService;
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
    private ShowSeatService showSeatService;

    @GetMapping
    public ResponseEntity<List<ShowSeat>> getAllShowSeats() {
        return new ResponseEntity<>(showSeatService.getAllShowSeats(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getShowSeatById(@PathVariable Long id) {
        Optional<ShowSeat> showSeat = showSeatService.getShowSeatById(id);
        if (showSeat.isPresent()) {
            return new ResponseEntity<>(showSeat.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ShowSeat not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ShowSeat> createShowSeat(@RequestBody ShowSeat showSeat) {
        ShowSeat savedShowSeat = showSeatService.createShowSeat(showSeat);
        return new ResponseEntity<>(savedShowSeat, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateShowSeat(@PathVariable Long id, @RequestBody ShowSeat showSeatDetails) {
        Optional<ShowSeat> updatedShowSeat = showSeatService.updateShowSeat(id, showSeatDetails);
        if (!updatedShowSeat.isPresent()) {
            return new ResponseEntity<>("ShowSeat not found", HttpStatus.NOT_FOUND);
        }
        ShowSeat showSeat = updatedShowSeat.get();
        showSeat.setSeatNumber(showSeatDetails.getSeatNumber()); // Correct method name
        showSeat.setSeatStatus(showSeatDetails.getSeatStatus());
        showSeat.setSeatType(showSeatDetails.getSeatType());
        showSeat.setShow(showSeatDetails.getShow());
        showSeat.setBooking(showSeatDetails.getBooking());
        return new ResponseEntity<>(showSeatService.updateShowSeat(id, showSeat), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteShowSeat(@PathVariable Long id) {
        boolean isDeleted = showSeatService.deleteShowSeat(id);
        if (!isDeleted) {
            return new ResponseEntity<>("ShowSeat not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("ShowSeat deleted successfully", HttpStatus.OK);
    }
}
