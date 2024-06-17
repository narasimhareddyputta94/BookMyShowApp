// src/services/showService.js
import axios from 'axios';

const API_URL = '/shows'; // Proxy will handle the base URL

export const getShows = () => {
    return axios.get(API_URL);
};

export const createShow = (show) => {
    return axios.post(API_URL, show);
};
