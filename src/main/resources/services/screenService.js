// src/services/screenService.js
import axios from 'axios';

const API_URL = '/screens'; // Proxy will handle the base URL

export const getScreens = () => {
    return axios.get(API_URL);
};

export const createScreen = (screen) => {
    return axios.post(API_URL, screen);
};
