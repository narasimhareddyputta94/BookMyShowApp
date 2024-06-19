package com.bookmyshow.demo.services;

import com.bookmyshow.demo.models.Booking;
import com.bookmyshow.demo.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class BookingServiceImpl implements BookingService {
    private static final Logger LOGGER = Logger.getLogger(BookingServiceImpl.class.getName());

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    @Transactional
    public Booking createBooking(Booking booking) {
        try {
            LOGGER.info("Creating booking with details: " + booking.toString());
            Booking savedBooking = bookingRepository.save(booking);
            LOGGER.info("Booking created successfully with ID: " + savedBooking.getId());
            return savedBooking;
        } catch (Exception e) {
            LOGGER.severe("Error creating booking: " + e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public Booking updateBooking(Long id, Booking bookingDetails) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setBookingStatus(bookingDetails.getBookingStatus());
            booking.setAmount(bookingDetails.getAmount());
            booking.setLockTime(bookingDetails.getLockTime());
            booking.setLocked(bookingDetails.isLocked());
            booking.setUser(bookingDetails.getUser());
            booking.setShow(bookingDetails.getShow());
            try {
                Booking updatedBooking = bookingRepository.save(booking);
                LOGGER.info("Booking updated successfully with ID: " + updatedBooking.getId());
                return updatedBooking;
            } catch (Exception e) {
                LOGGER.severe("Error updating booking: " + e.getMessage());
                throw e;
            }
        }).orElse(null);
    }

    @Override
    @Transactional
    public boolean deleteBooking(Long id) {
        return bookingRepository.findById(id).map(booking -> {
            try {
                bookingRepository.delete(booking);
                LOGGER.info("Booking deleted successfully with ID: " + booking.getId());
                return true;
            } catch (Exception e) {
                LOGGER.severe("Error deleting booking: " + e.getMessage());
                throw e;
            }
        }).orElse(false);
    }
}
