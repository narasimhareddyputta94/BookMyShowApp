// src/services/regionService.js
import axios from 'axios';

const API_URL = '/regions'; // Proxy will handle the base URL

export const getRegions = () => {
    return axios.get(API_URL);
};

export const createRegion = (region) => {
    return axios.post(API_URL, region);
};
