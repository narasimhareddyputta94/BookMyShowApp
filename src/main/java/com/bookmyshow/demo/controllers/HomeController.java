package com.bookmyshow.demo.controllers;

import com.bookmyshow.demo.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/")
    public String home() {
        return homeService.getWelcomeMessage();
    }

    @GetMapping("/info")
    public String getAppInfo() {
        return homeService.getAppInfo();
    }

    @GetMapping("/status")
    public String getStatus() {
        return homeService.getStatus();
    }
}
