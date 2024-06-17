package com.bookmyshow.demo.controllers;

import com.bookmyshow.demo.models.Booking;
import com.bookmyshow.demo.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return new ResponseEntity<>(bookingRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            return new ResponseEntity<>(booking.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking savedBooking = bookingRepository.save(booking);
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBooking(@PathVariable Long id, @RequestBody Booking bookingDetails) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (!bookingOptional.isPresent()) {
            return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
        }
        Booking booking = bookingOptional.get();
        booking.setBookingStatus(bookingDetails.getBookingStatus());
        booking.setAmount(bookingDetails.getAmount());
        booking.setPayments(bookingDetails.getPayments());
        booking.setShowSeats(bookingDetails.getShowSeats());
        bookingRepository.save(booking);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBooking(@PathVariable Long id) {
        if (!bookingRepository.existsById(id)) {
            return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
        }
        bookingRepository.deleteById(id);
        return new ResponseEntity<>("Booking deleted successfully", HttpStatus.OK);
    }
}
