// src/services/seatService.js
import axios from 'axios';

const API_URL = '/seats'; // Proxy will handle the base URL

export const getSeats = () => {
    return axios.get(API_URL);
};

export const createSeat = (seat) => {
    return axios.post(API_URL, seat);
};
