import React from 'react';
import './App.css';

// NLS
import { useTranslation } from "react-i18next";
import "../../nls/i18n";

// Router 
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

// Composants
import Dashboard from '../Dashboard/Dashboard';
import Preferences from '../Preferences/Preferences';


const App = function () {
    const { t } = useTranslation();
    const argnls = {
        val: 'toto'
    }
    const val = 'toto';
    
    return (
        <div className="App">
            <h1>Application</h1>
            <Router>
                <Routes>
                    <Route path="/dashboard">
                        <Dashboard />
                    </Route>
                    <Route path="/preferences">
                        <Preferences />
                    </Route>
                </Routes>
            </Router>
        </div>
        );
    }
    
    export default App;