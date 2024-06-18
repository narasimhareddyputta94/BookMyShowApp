package com.bookmyshow.demo.services;

import com.bookmyshow.demo.models.ShowSeat;
import com.bookmyshow.demo.repositories.ShowSeatRepositor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowSeatService {

    @Autowired
    private ShowSeatRepositor showSeatRepository;

    public List<ShowSeat> getAllShowSeats() {
        return showSeatRepository.findAll();
    }

    public Optional<ShowSeat> getShowSeatById(Long id) {
        return showSeatRepository.findById(id);
    }

    public ShowSeat createShowSeat(ShowSeat showSeat) {
        return showSeatRepository.save(showSeat);
    }

    public Optional<ShowSeat> updateShowSeat(Long id, ShowSeat showSeatDetails) {
        Optional<ShowSeat> showSeatOptional = showSeatRepository.findById(id);
        if (showSeatOptional.isPresent()) {
            ShowSeat showSeat = showSeatOptional.get();
            showSeat.setSeatNumber(showSeatDetails.getSeatNumber());
            showSeat.setSeatStatus(showSeatDetails.getSeatStatus());
            showSeat.setSeatType(showSeatDetails.getSeatType());
            showSeat.setShow(showSeatDetails.getShow());
            showSeat.setBooking(showSeatDetails.getBooking());
            return Optional.of(showSeatRepository.save(showSeat));
        }
        return Optional.empty();
    }

    public boolean deleteShowSeat(Long id) {
        if (showSeatRepository.existsById(id)) {
            showSeatRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
