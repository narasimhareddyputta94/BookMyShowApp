package com.bookmyshow.demo.services;

import com.bookmyshow.demo.models.Screen;
import com.bookmyshow.demo.repositories.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    public List<Screen> getAllScreens() {
        return screenRepository.findAll();
    }

    public Optional<Screen> getScreenById(Long id) {
        return screenRepository.findById(id);
    }

    public Screen saveScreen(Screen screen) {
        return screenRepository.save(screen);
    }

    public Screen updateScreen(Long id, Screen screenDetails) {
        Optional<Screen> screenOptional = screenRepository.findById(id);
        if (screenOptional.isPresent()) {
            Screen screen = screenOptional.get();
            screen.setName(screenDetails.getName());
            screen.setSeats(screenDetails.getSeats()); // make sure getSeats() is available
            return screenRepository.save(screen);
        } else {
            throw new RuntimeException("Screen not found with id " + id);
        }
    }

    public void deleteScreen(Long id) {
        screenRepository.deleteById(id);
    }
}
