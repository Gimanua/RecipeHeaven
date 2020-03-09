import React from 'react';
import './styling/NavigationBar.scss';

/**
 * The Navigation Bar available at the top of every page.
 * @param {Object} props The React props object
 * @param {'home' | 'recipes'} props.currentPage The page you're currently on
 * @param {Function} props.onPageChange  Callback receiving the new page navigated to
 * @param {Boolean} props.loggedIn Whether or not the user is logged in
 * @param {Function} props.logIn Callback when the user presses the login button
 * @param {Function} props.logOut Callback when the user presses the logout button
 */
export default function NavigationBar({ currentPage, onPageChange, loggedIn, logIn, logOut }) {
    return (
        <nav className="has-text-white">
            <ul className="columns is-gapless">
                <li className="column">
                    <button className={`button is-fullwidth is-large${currentPage==='home' ? ' has-text-weight-bold':''}`} onClick={() => onPageChange('home')}>
                        Hem
                    </button>
                </li>
                <li className="column">
                    <button className={`button is-fullwidth is-large${currentPage==='recipes' ? ' has-text-weight-bold':''}`} onClick={() => onPageChange('recipes')}>
                        Recept
                    </button>
                </li>
                <li className="column">
                    <button className={`button is-fullwidth is-large`} onClick={() => {
                        if(loggedIn)
                            logOut();
                        else
                            logIn();
                    }}>
                        {loggedIn ? 'Logga Ut' : 'Logga In'}
                    </button>
                </li>
            </ul>
        </nav>
    );
}