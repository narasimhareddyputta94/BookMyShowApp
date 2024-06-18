package com.bookmyshow.demo.services;

import org.springframework.stereotype.Service;

@Service
public class HomeService {

    public String getWelcomeMessage() {
        return "Welcome to the BookMyShow API!";
    }

    public String getAppInfo() {
        return "BookMyShow API Version 1.0.0 - Serving movie bookings since 2024.";
    }

    public String getStatus() {
        return "All systems are operational.";
    }
}
