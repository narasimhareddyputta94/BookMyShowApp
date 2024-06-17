package com.bookmyshow.demo.controllers;

import com.bookmyshow.demo.models.Movie;
import com.bookmyshow.demo.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    /**
     * Retrieves all movies from the database.
     *
     * @return A list of all movies with HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return new ResponseEntity<>(movieRepository.findAll(), HttpStatus.OK);
    }

    /**
     * Retrieves a movie by its ID.
     *
     * @param id The ID of the movie to retrieve.
     * @return The movie if found, otherwise an error message with HTTP status 404 (Not Found).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getMovieById(@PathVariable Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a new movie.
     *
     * @param movie The movie details to create.
     * @return The created movie with HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }

    /**
     * Updates an existing movie by its ID.
     *
     * @param id The ID of the movie to update.
     * @param movieDetails The new movie details.
     * @return The updated movie with HTTP status 200 (OK), or an error message if the movie is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (!movieOptional.isPresent()) {
            return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
        }
        Movie movie = movieOptional.get();
        movie.setTitle(movieDetails.getTitle());
        movie.setGenre(movieDetails.getGenre());
        movie.setDuration(movieDetails.getDuration());
        movieRepository.save(movie);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    /**
     * Deletes a movie by its ID.
     *
     * @param id The ID of the movie to delete.
     * @return A success message with HTTP status 200 (OK) if the movie is deleted, or an error message if the movie is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMovie(@PathVariable Long id) {
        if (!movieRepository.existsById(id)) {
            return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
        }
        movieRepository.deleteById(id);
        return new ResponseEntity<>("Movie deleted successfully", HttpStatus.OK);
    }
}
