// src/services/userService.js
import axios from 'axios';

const API_URL = '/users'; // Proxy will handle the base URL

export const getUsers = () => {
    return axios.get(API_URL);
};

export const createUser = (user) => {
    return axios.post(API_URL, user);
};
