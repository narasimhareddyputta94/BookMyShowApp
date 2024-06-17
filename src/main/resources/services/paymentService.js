// src/services/paymentService.js
import axios from 'axios';

const API_URL = '/payments'; // Proxy will handle the base URL

export const getPayments = () => {
    return axios.get(API_URL);
};

export const createPayment = (payment) => {
    return axios.post(API_URL, payment);
};
