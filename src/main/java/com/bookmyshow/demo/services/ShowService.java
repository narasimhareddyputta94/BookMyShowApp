package com.bookmyshow.demo.services;

import com.bookmyshow.demo.models.Show;
import com.bookmyshow.demo.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    public Optional<Show> getShowById(Long id) {
        return showRepository.findById(id);
    }

    public Show createShow(Show show) {
        return showRepository.save(show);
    }

    public Show updateShow(Long id, Show showDetails) {
        Optional<Show> showOptional = showRepository.findById(id);
        if (showOptional.isPresent()) {
            Show show = showOptional.get();
            show.setStartTime(showDetails.getStartTime());
            show.setEndTime(showDetails.getEndTime());
            show.setMovie(showDetails.getMovie());
            show.setScreen(showDetails.getScreen());
            show.setShowSeatTypes(showDetails.getShowSeatTypes());
            return showRepository.save(show);
        }
        return null;
    }

    public void deleteShow(Long id) {
        showRepository.deleteById(id);
    }
}
