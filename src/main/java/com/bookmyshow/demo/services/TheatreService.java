package com.bookmyshow.demo.services;

import com.bookmyshow.demo.models.Theatre;
import com.bookmyshow.demo.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheatreService {

    private final TheatreRepository theatreRepository;

    @Autowired
    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    public Optional<Theatre> getTheatreById(Long id) {
        return theatreRepository.findById(id);
    }

    public Theatre createTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }

    public Theatre updateTheatre(Long id, Theatre theatreDetails) {
        return theatreRepository.findById(id).map(theatre -> {
            theatre.setName(theatreDetails.getName());
            theatre.setAddress(theatreDetails.getAddress());
            theatre.setRegion(theatreDetails.getRegion());
            return theatreRepository.save(theatre);
        }).orElseThrow(() -> new RuntimeException("Theatre not found with id " + id));
    }

    public void deleteTheatre(Long id) {
        if (!theatreRepository.existsById(id)) {
            throw new RuntimeException("Theatre not found with id " + id);
        }
        theatreRepository.deleteById(id);
    }
}
