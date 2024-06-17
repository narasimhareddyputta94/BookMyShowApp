// src/services/theatreService.js
import axios from 'axios';

const API_URL = '/theatres'; // Proxy will handle the base URL

export const getTheatres = () => {
    return axios.get(API_URL);
};

export const createTheatre = (theatre) => {
    return axios.post(API_URL, theatre);
};
