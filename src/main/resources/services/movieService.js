// src/services/movieService.js
import axios from 'axios';

const API_URL = '/movies'; // Proxy will handle the base URL

export const getMovies = () => {
    return axios.get(API_URL);
};

export const createMovie = (movie) => {
    return axios.post(API_URL, movie);
};
