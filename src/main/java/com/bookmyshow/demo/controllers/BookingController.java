package com.bookmyshow.demo.controllers;

import com.bookmyshow.demo.models.Booking;
import com.bookmyshow.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.getBookingById(id);
        if (booking.isPresent()) {
            return new ResponseEntity<>(booking.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Transactional(timeout = 120, isolation = Isolation.SERIALIZABLE)
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        booking.setLocked(true);
        booking.setLockTime(LocalDateTime.now());
        Booking savedBooking = bookingService.createBooking(booking);
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Transactional(timeout = 120, isolation = Isolation.SERIALIZABLE)
    public ResponseEntity<Object> updateBooking(@PathVariable Long id, @RequestBody Booking bookingDetails) {
        Optional<Booking> bookingOptional = Optional.ofNullable(bookingService.updateBooking(id, bookingDetails));
        if (!bookingOptional.isPresent()) {
            return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookingOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional(timeout = 120, isolation = Isolation.SERIALIZABLE)
    public ResponseEntity<Object> deleteBooking(@PathVariable Long id) {
        boolean isDeleted = bookingService.deleteBooking(id);
        if (!isDeleted) {
            return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Booking deleted successfully", HttpStatus.OK);
    }
}
