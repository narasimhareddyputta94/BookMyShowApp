package com.bookmyshow.demo.services;

import com.bookmyshow.demo.models.Theatre;
import com.bookmyshow.demo.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Theatreservice {
    @Autowired
    private TheatreRepository theatreRepository;

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
        Optional<Theatre> theatreOptional = theatreRepository.findById(id);
        if (theatreOptional.isPresent()) {
            Theatre theatre = theatreOptional.get();
            theatre.setName(theatreDetails.getName());
            theatre.setAddress(theatreDetails.getAddress());
            theatre.setRegion(theatreDetails.getRegion());
            return theatreRepository.save(theatre);

        }
        return null;
    }

    public void deleteTheatre(Long id) {
        theatreRepository.deleteById(id);
    }


}
