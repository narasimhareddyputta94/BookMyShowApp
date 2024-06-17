// resources/App.js
import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import BookingPage from './pages/BookingPage';
import HomePage from './pages/HomePage';
import MoviePage from './pages/MoviePage';
import PaymentPage from './pages/PaymentPage';
import RegionPage from './pages/RegionPage';
import ScreenPage from './pages/ScreenPage';
import SeatPage from './pages/SeatPage';
import ShowPage from './pages/ShowPage';
import TheatrePage from './pages/TheatrePage';
import UserPage from './pages/UserPage';

const App = () => {
    return (
        <Router>
            <Switch>
                <Route path="/" exact component={HomePage} />
                <Route path="/bookings" component={BookingPage} />
                <Route path="/movies" component={MoviePage} />
                <Route path="/payments" component={PaymentPage} />
                <Route path="/regions" component={RegionPage} />
                <Route path="/screens" component={ScreenPage} />
                <Route path="/seats" component={SeatPage} />
                <Route path="/shows" component={ShowPage} />
                <Route path="/theatres" component={TheatrePage} />
                <Route path="/users" component={UserPage} />
            </Switch>
        </Router>
    );
};

export default App;
