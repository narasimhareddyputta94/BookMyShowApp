package com.bookmyshow.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
//
//	@Bean
//	public CommandLineRunner demo(TheatreRepository theatreRepository, ScreenRepository screenRepository, MovieRepository movieRepository, ShowRepository showRepository, UserRepository userRepository, BookingRepository bookingRepository) {
//		return (args) -> {
//			// Insert theatres
//			Theatre theatre1 = new Theatre();
//			theatre1.setName("PVR");
//			theatre1.setAddress("Bangalore");
//			theatreRepository.save(theatre1);
//
//			Theatre theatre2 = new Theatre();
//			theatre2.setName("Inox");
//			theatre2.setAddress("Bangalore");
//			theatreRepository.save(theatre2);
//
//			// Insert screens
//			Screen screen1 = new Screen();
//			screen1.setScreenNumber(1);
//			screen1.setTheatre(theatre1);
//			screenRepository.save(screen1);
//
//			Screen screen2 = new Screen();
//			screen2.setScreenNumber(2);
//			screen2.setTheatre(theatre1);
//			screenRepository.save(screen2);
//
//			Screen screen3 = new Screen();
//			screen3.setScreenNumber(1);
//			screen3.setTheatre(theatre2);
//			screenRepository.save(screen3);
//
//			// Insert movies
//			Movie movie1 = new Movie();
//			movie1.setName("Movie1");
//			movie1.setLanguage("English");
//			movie1.setDuration(120);
//			movieRepository.save(movie1);
//
//			Movie movie2 = new Movie();
//			movie2.setName("Movie2");
//			movie2.setLanguage("Hindi");
//			movie2.setDuration(150);
//			movieRepository.save(movie2);
//
//			// Insert shows
//			Show show1 = new Show();
//			show1.setMovie(movie1);
//			show1.setScreen(screen1);
//			show1.setStartTime(new Date());
//			show1.setEndTime(new Date());
//			showRepository.save(show1);
//
//			Show show2 = new Show();
//			show2.setMovie(movie2);
//			show2.setScreen(screen2);
//			show2.setStartTime(new Date());
//			show2.setEndTime(new Date());
//			showRepository.save(show2);
//
//			// Insert users
//			User user1 = new User();
//			user1.setEmail("user1@example.com");
//			user1.setName("User 1");
//			user1.setPassword("password1");
//			user1.setPhoneNumber("1234567890");
//			userRepository.save(user1);
//
//			User user2 = new User();
//			user2.setEmail("user2@example.com");
//			user2.setName("User 2");
//			user2.setPassword("password2");
//			user2.setPhoneNumber("0987654321");
//			userRepository.save(user2);
//
//			// Insert bookings
//			Booking booking1 = new Booking();
//			booking1.setAmount(100.00);
//			booking1.setBookingStatus(BookingStatus.CONFIRMED);
//			booking1.setShow(show1);
//			booking1.setUser(user1);
//			booking1.setLocked(false);
//			bookingRepository.save(booking1);
//
//			Booking booking2 = new Booking();
//			booking2.setAmount(150.00);
//			booking2.setBookingStatus(BookingStatus.CONFIRMED);
//			booking2.setShow(show2);
//			booking2.setUser(user2);
//			booking2.setLocked(false);
//			bookingRepository.save(booking2);
//		};
	}

