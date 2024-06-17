package com.bookmyshow.demo.scheduling;

import com.bookmyshow.demo.models.Booking;
import com.bookmyshow.demo.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class LockReleaseTask {

    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Scheduled task to release expired locks.
     * This runs every minute to check for bookings locked for more than 2 minutes.
     */
    @Scheduled(fixedRate = 60000)  // Check every minute
    public void releaseExpiredLocks() {
        LocalDateTime expirationTime = LocalDateTime.now().minusMinutes(2);
        List<Booking> lockedBookings = bookingRepository.findAllLockedBefore(expirationTime);
        for (Booking booking : lockedBookings) {
            booking.setLocked(false);
            booking.setLockTime(null);
            bookingRepository.save(booking);
        }
    }
}
