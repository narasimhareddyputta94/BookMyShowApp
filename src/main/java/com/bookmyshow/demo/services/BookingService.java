package com.bookmyshow.demo.services;

import com.bookmyshow.demo.models.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    List<Booking> getAllBookings();
    Optional<Booking> getBookingById(Long id);
    Booking createBooking(Booking booking);
    Booking updateBooking(Long id, Booking bookingDetails);
    boolean deleteBooking(Long id);
}
