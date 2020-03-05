import React from 'react';
import './styling/Categories.scss';

export default function Categories(props) {

    return (
        <>
            <h2 className="title">Kategorier</h2>

            <input className="category-checkbox" type="checkbox" name="category-meat" id="category-meat" value="meat" />
            <label className="is-size-2" htmlFor="category-meat">Kött</label><br />

            <input className="category-checkbox" type="checkbox" name="category-vegetarian" id="category-vegetarian" value="vegetarian" />
            <label className="is-size-2" htmlFor="category-vegetarian">Vegetariskt</label><br />
        </>
    );
}