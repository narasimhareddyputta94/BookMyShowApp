// resources/components/ScreenComponent.js
import React, { useEffect, useState } from 'react';
import { getScreens } from '../services/screenService';

const ScreenComponent = () => {
    const [screens, setScreens] = useState([]);

    useEffect(() => {
        getScreens().then(response => {
            setScreens(response.data);
        }).catch(error => {
            console.error('Error fetching screens:', error);
        });
    }, []);

    return (
        <div>
            <h2>Screens</h2>
            <ul>
                {screens.map(screen => (
                    <li key={screen.id}>{screen.name}</li>
                ))}
            </ul>
        </div>
    );
};

export default ScreenComponent;
