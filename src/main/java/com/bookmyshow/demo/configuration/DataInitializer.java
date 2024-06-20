package com.bookmyshow.demo.configuration;


import com.bookmyshow.demo.models.User;
import com.bookmyshow.demo.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        if (userRepository.findAll().isEmpty()) {
            User user = new User();
            user.setName("Test User");
            user.setEmail("test@example.com");
            user.setPassword("password");
            userRepository.save(user);
        }
    }
}
