import React from 'react';
import './css/NavigationBar.scss';

/**
 * The Navigation Bar available at the top of every page.
 * @param {Object} props The React props object
 * @param {'home' | 'recipes' | 'registerAndLogin'} props.currentPage The page you're currently on
 */
export default function NavigationBar({ currentPage }) {
    return (
        <nav className="has-text-white">
            <ul className="columns is-marginless">
                <li className="column has-text-centered">Hem</li>
                <li className="column has-text-centered">Recept</li>
                <li className="column has-text-centered">Registrera & Logga In</li>
            </ul>
        </nav>
    );
}