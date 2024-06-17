package com.bookmyshow.demo.services;

import com.bookmyshow.demo.models.Seat;
import com.bookmyshow.demo.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public Optional<Seat> getSeatById(Long id) {
        return seatRepository.findById(id);
    }

    public Seat saveSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    public Optional<Seat> updateSeat(Long id, Seat seatDetails) {
        Optional<Seat> seatOptional = seatRepository.findById(id);
        if (seatOptional.isPresent()) {
            Seat seat = seatOptional.get();
            seat.setSeatNumber(seatDetails.getSeatNumber());
            seat.setSeatStatus(seatDetails.getSeatStatus());
            seat.setScreen(seatDetails.getScreen());
            seat.setSeatType(seatDetails.getSeatType());
            return Optional.of(seatRepository.save(seat));
        } else {
            return Optional.empty();
        }
    }

    public void deleteSeat(Long id) {
        seatRepository.deleteById(id);
    }
}
