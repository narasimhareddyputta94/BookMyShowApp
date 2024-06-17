// resources/components/ShowComponent.js
import React, { useEffect, useState } from 'react';
import { getShows } from '../services/showService';

const ShowComponent = () => {
    const [shows, setShows] = useState([]);

    useEffect(() => {
        getShows().then(response => {
            setShows(response.data);
        }).catch(error => {
            console.error('Error fetching shows:', error);
        });
    }, []);

    return (
        <div>
            <h2>Shows</h2>
            <ul>
                {shows.map(show => (
                    <li key={show.id}>{show.name}</li>
                ))}
            </ul>
        </div>
    );
};

export default ShowComponent;
