package com.bookmyshow.demo.controllers;

import com.bookmyshow.demo.models.Booking;
import com.bookmyshow.demo.repositories.BookingRepository;
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
    private BookingRepository bookingRepository;

    /**
     * Retrieves all bookings from the database.
     *
     * @return A list of all bookings with HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return new ResponseEntity<>(bookingRepository.findAll(), HttpStatus.OK);
    }

    /**
     * Retrieves a booking by its ID.
     *
     * @param id The ID of the booking to retrieve.
     * @return The booking if found, otherwise an error message with HTTP status 404 (Not Found).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            return new ResponseEntity<>(booking.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a new booking.
     *
     * @param booking The booking details to create.
     * @return The created booking with HTTP status 201 (Created).
     */
    @PostMapping
    @Transactional(timeout = 120, isolation = Isolation.SERIALIZABLE)  // 2 minutes timeout, Serializable isolation level
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        booking.setLocked(true);
        booking.setLockTime(LocalDateTime.now());
        Booking savedBooking = bookingRepository.save(booking);
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }

    /**
     * Updates an existing booking by its ID.
     *
     * @param id The ID of the booking to update.
     * @param bookingDetails The new booking details.
     * @return The updated booking with HTTP status 200 (OK), or an error message if the booking is not found.
     */
    @PutMapping("/{id}")
    @Transactional(timeout = 120, isolation = Isolation.SERIALIZABLE)  // 2 minutes timeout, Serializable isolation level
    public ResponseEntity<Object> updateBooking(@PathVariable Long id, @RequestBody Booking bookingDetails) {
        Optional<Booking> bookingOptional = bookingRepository.findByIdWithLock(id);
        if (!bookingOptional.isPresent()) {
            return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
        }
        Booking booking = bookingOptional.get();
        booking.setBookingStatus(bookingDetails.getBookingStatus());
        booking.setAmount(bookingDetails.getAmount());
        booking.setPayments(bookingDetails.getPayments());
        booking.setShowSeats(bookingDetails.getShowSeats());
        booking.setLocked(true);
        booking.setLockTime(LocalDateTime.now());
        bookingRepository.save(booking);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    /**
     * Deletes a booking by its ID.
     *
     * @param id The ID of the booking to delete.
     * @return A success message with HTTP status 200 (OK) if the booking is deleted, or an error message if the booking is not found.
     */
    @DeleteMapping("/{id}")
    @Transactional(timeout = 120, isolation = Isolation.SERIALIZABLE)  // 2 minutes timeout, Serializable isolation level
    public ResponseEntity<Object> deleteBooking(@PathVariable Long id) {
        if (!bookingRepository.existsById(id)) {
            return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
        }
        bookingRepository.deleteById(id);
        return new ResponseEntity<>("Booking deleted successfully", HttpStatus.OK);
    }
}
