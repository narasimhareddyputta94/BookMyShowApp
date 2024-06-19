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
import java.util.logging.Logger;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private static final Logger LOGGER = Logger.getLogger(BookingController.class.getName());

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
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
    public ResponseEntity<Object> createBooking(@RequestBody Booking booking) {
        try {
            LOGGER.info("Creating booking with details: " + booking.toString());
            booking.setLocked(true);
            booking.setLockTime(LocalDateTime.now());
            Booking savedBooking = bookingService.createBooking(booking);
            LOGGER.info("Booking created successfully with ID: " + savedBooking.getId());
            return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.severe("Error creating booking: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("Error creating booking: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Transactional(timeout = 120, isolation = Isolation.SERIALIZABLE)
    public ResponseEntity<Object> updateBooking(@PathVariable Long id, @RequestBody Booking bookingDetails) {
        try {
            LOGGER.info("Updating booking with ID: " + id + " and details: " + bookingDetails.toString());
            Optional<Booking> bookingOptional = Optional.ofNullable(bookingService.updateBooking(id, bookingDetails));
            if (!bookingOptional.isPresent()) {
                return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(bookingOptional.get(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.severe("Error updating booking: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("Error updating booking: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Transactional(timeout = 120, isolation = Isolation.SERIALIZABLE)
    public ResponseEntity<Object> deleteBooking(@PathVariable Long id) {
        try {
            LOGGER.info("Deleting booking with ID: " + id);
            boolean isDeleted = bookingService.deleteBooking(id);
            if (!isDeleted) {
                return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Booking deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.severe("Error deleting booking: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("Error deleting booking: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
