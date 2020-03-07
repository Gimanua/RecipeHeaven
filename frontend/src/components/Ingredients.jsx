import React from 'react';
import RecipeIngredient from './RecipeIngredient';

export default function Ingredients({ ingredients, addIngredient, removeIngredient }) {

    const [showAddIngredient, setShowAddIngredient] = React.useState(false);
    console.log(ingredients);

    return (
        <>
            <label className="label" htmlFor="ingredients">Ingredienser</label>
            <div className="field" id="ingredients">
                <div className="control">
                    <button className={`button ${ingredients.length > 0 ? 'is-success' : 'is-danger'}`} onClick={() => setShowAddIngredient(true)}>Lägg till Ingrediens</button>
                </div>
                <p className={`help ${ingredients.length > 0 ? 'is-hidden' : 'is-danger'}`}>Du måste ange minst en ingrediens.</p>
            </div>
            <RecipeIngredient active={showAddIngredient} setActive={setShowAddIngredient} addIngredient={ingredient => addIngredient(ingredient)} />
            <div className="content">
                <ul id="ingredients">
                    {ingredients.map((ingredient, index) => <li key={index}>{`${ingredient.amount}${ingredient.unitAbbreviation} ${ingredient.name}`}</li>)}
                </ul>
            </div>
        </>
    );
}