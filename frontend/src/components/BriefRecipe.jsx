import React from 'react';
import './styling/BriefRecipe.scss';

/**
 * Component representing a Recipe in its Brief format (Image, name and description.)
 * @param {Object} props The React props Object
 * @param {import('../entities/Recipe').Recipe} props.recipe The recipe to display information about
 * @param {Function} props.onClick Callback when the recipe is clicked, send the id of the recipe
 */
export default function BriefRecipe({ recipe, onClick }) {
    return (
        <article className="card" onClick={() => onClick(recipe.id)}>
            <div className="card-header">
                <h2 className="card-header-title">{recipe.name}</h2>
            </div>
            <div className="card-image">
                <figure className="image">
                    <img src={recipe.image} alt={recipe.name} />
                </figure>
            </div>
            <div className="card-content">
                <p>{recipe.description}</p>
            </div>
        </article>
    );
}