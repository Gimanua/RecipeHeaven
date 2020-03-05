import React from 'react';
import Categories from './Categories';
import AddRecipeIngredients from './AddRecipeIngredients';
import AddRecipeInstructions from './AddRecipeInstructions';

export default function AddRecipe(props){
    return (
        <form onSubmit={(e) => e.preventDefault()} className="box" method="post">
            <label className="label" htmlFor="add-recipe-name">Namn på Rätt</label>
            <input className="input" type="text" id="add-recipe-name"/>

            <label className="label" htmlFor="add-recipe-description">Beskrivning av Rätt</label>
            <textarea className="textarea" name="description" id="add-recipe-description" rows="6"></textarea>

            <label className="label" htmlFor="add-recipe-image">Bild på Rätt</label>
            <input type="file" accept="image/*" name="image" id="add-recipe-image"/>

            <Categories />

            <AddRecipeIngredients />

            <AddRecipeInstructions />

            <button className="has-text-weight-bold button">Lägg till Recept</button>

        </form>
    );
}