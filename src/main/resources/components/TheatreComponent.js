// resources/components/TheatreComponent.js
import React, { useEffect, useState } from 'react';
import { getTheatres } from '../services/theatreService';

const TheatreComponent = () => {
    const [theatres, setTheatres] = useState([]);

    useEffect(() => {
        getTheatres().then(response => {
            setTheatres(response.data);
        }).catch(error => {
            console.error('Error fetching theatres:', error);
        });
    }, []);

    return (
        <div>
            <h2>Theatres</h2>
            <ul>
                {theatres.map(theatre => (
                    <li key={theatre.id}>{theatre.name}</li>
                ))}
            </ul>
        </div>
    );
};

export default TheatreComponent;
