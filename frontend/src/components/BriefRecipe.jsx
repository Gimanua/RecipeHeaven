import React from 'react';

/**
 * Component representing a Recipe in its Brief format (Image, name and description.)
 * @param {Object} props The React props Object
 * @param {String} props.image The image source
 * @param {String} props.name The name of the recipe
 * @param {String} props.description The description of the recipe
 */
export default function BriefRecipe({image, name, description}){
    return (
        <article className="card">
            <img className="card-image" src={image} alt={name} />
            <h2>{name}</h2>
            <p>{description}</p>
        </article>
    );
}