import React from 'react';

/**
 * Component representing a Recipe in its Brief format (Image, name and description.)
 * @param {Object} props The React props Object
 * @param {String} props.image The image source
 * @param {String} props.name The name of the recipe
 * @param {String} props.description The description of the recipe
 */
export default function BriefRecipe({ image, name, description }) {
    return (
        <article className="card">
            <div className="card-header">
                <h2 className="card-header-title">{name}</h2>
            </div>
            <img className="card-image" src={image} alt={name} />
            <div className="card-content">

                <p>{description}</p>
            </div>
        </article>
    );
}