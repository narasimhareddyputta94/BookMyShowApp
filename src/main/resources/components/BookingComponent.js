// resources/components/BookingComponent.js
import React, { useEffect, useState } from 'react';
import { getBookings } from '../services/bookingService';

const BookingComponent = () => {
    const [bookings, setBookings] = useState([]);

    useEffect(() => {
        getBookings().then(response => {
            setBookings(response.data);
        }).catch(error => {
            console.error('Error fetching bookings:', error);
        });
    }, []);

    return (
        <div>
            <h2>Bookings</h2>
            <ul>
                {bookings.map(booking => (
                    <li key={booking.id}>{booking.bookingStatus} - ${booking.amount}</li>
                ))}
            </ul>
        </div>
    );
};

export default BookingComponent;
