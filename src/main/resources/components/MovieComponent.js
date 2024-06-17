// resources/components/MovieComponent.js
import React, { useEffect, useState } from 'react';
import { getMovies } from '../services/movieService';

const MovieComponent = () => {
    const [movies, setMovies] = useState([]);

    useEffect(() => {
        getMovies().then(response => {
            setMovies(response.data);
        }).catch(error => {
            console.error('Error fetching movies:', error);
        });
    }, []);

    return (
        <div>
            <h2>Movies</h2>
            <ul>
                {movies.map(movie => (
                    <li key={movie.id}>{movie.title}</li>
                ))}
            </ul>
        </div>
    );
};

export default MovieComponent;
// The MovieComponent is similar to the BookingComponent. It fetches movies from the backend and renders them in a list. The only difference is that it fetches movies instead of bookings.