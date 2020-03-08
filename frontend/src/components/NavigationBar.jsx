import React from 'react';
import './styling/NavigationBar.scss';

/**
 * The Navigation Bar available at the top of every page.
 * @param {Object} props The React props object
 * @param {'home' | 'recipes' | 'registerAndLogin'} props.currentPage The page you're currently on
 */
export default function NavigationBar({ currentPage, onPageChange }) {
    return (
        <nav className="has-text-white">
            <ul className="columns is-gapless">
                <li className="column"><button className={`button is-fullwidth is-large${currentPage==='home' ? ' has-text-weight-bold':''}`} onClick={() => onPageChange('home')}>Hem</button></li>
                <li className="column"><button className={`button is-fullwidth is-large${currentPage==='recipes' ? ' has-text-weight-bold':''}`} onClick={() => onPageChange('recipes')}>Recept</button></li>
                <li className="column"><button className={`button is-fullwidth is-large${currentPage==='registerAndLogin' ? ' has-text-weight-bold':''}`} onClick={() => onPageChange('registerAndLogin')}>Registrera & Logga In</button></li>
            </ul>
        </nav>
    );
}