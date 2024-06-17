// resources/components/SeatComponent.js
import React, { useEffect, useState } from 'react';
import { getSeats } from '../services/seatService';

const SeatComponent = () => {
    const [seats, setSeats] = useState([]);

    useEffect(() => {
        getSeats().then(response => {
            setSeats(response.data);
        }).catch(error => {
            console.error('Error fetching seats:', error);
        });
    }, []);

    return (
        <div>
            <h2>Seats</h2>
            <ul>
                {seats.map(seat => (
                    <li key={seat.id}>{seat.number} - {seat.status}</li>
                ))}
            </ul>
        </div>
    );
};

export default SeatComponent;
