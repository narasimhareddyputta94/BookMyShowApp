// resources/components/RegionComponent.js
import React, { useEffect, useState } from 'react';
import { getRegions } from '../services/regionService';

const RegionComponent = () => {
    const [regions, setRegions] = useState([]);

    useEffect(() => {
        getRegions().then(response => {
            setRegions(response.data);
        }).catch(error => {
            console.error('Error fetching regions:', error);
        });
    }, []);

    return (
        <div>
            <h2>Regions</h2>
            <ul>
                {regions.map(region => (
                    <li key={region.id}>{region.name}</li>
                ))}
            </ul>
        </div>
    );
};

export default RegionComponent;
