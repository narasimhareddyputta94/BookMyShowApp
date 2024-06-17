// src/services/bookingService.js
import axios from 'axios';

const API_URL = '/bookings'; // Proxy will handle the base URL

export const getBookings = () => {
    return axios.get(API_URL);
};

export const createBooking = (booking) => {
    return axios.post(API_URL, booking);
};
