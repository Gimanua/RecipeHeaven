import React from 'react';
import Filter from './Filter';
import BriefRecipe from './BriefRecipe';
import AddRecipe from './AddRecipe';
import { RecipeBuilder } from '../entities/Recipe';

const fakeRecipes = [
    new RecipeBuilder().setId(1).setImage('https://cdn.pixabay.com/photo/2017/01/30/13/49/pancakes-2020863_960_720.jpg').setName('Pannkakor').setDescription('Enkelt att laga och gott att äta, med detta recept så blir din lunch eller middag eller fika en enkel match. Rätten kräver oerhört lite från kocken och kylen och såklart plånboken.').build(),
    new RecipeBuilder().setId(2).setImage('https://cdn.pixabay.com/photo/2017/01/30/13/49/pancakes-2020863_960_720.jpg').setName('Pannkakor').setDescription('Enkelt att laga och gott att äta, med detta recept så blir din lunch eller middag eller fika en enkel match. Rätten kräver oerhört lite från kocken och kylen och såklart plånboken.').build()
];

/**
 * 
 * @param {Object} props The React props Object
 * @param {Boolean} props.loggedIn Whether or not the user is logged in
 * @param {Function} props.expandRecipe Callback when the user request more information about a recipe, send the id of the recipe
 */
export default function Recipes({loggedIn, expandRecipe}){

    const [showAddRecipe, setShowAddRecipe] = React.useState(false);

    return (
        <>
            <Filter />
            {loggedIn && <button onClick={() => setShowAddRecipe(true)} className="button is-fullwidth has-text-weight-semibold is-medium">+ Lägg till ett eget Recept</button>}
            {showAddRecipe && <AddRecipe close={() => setShowAddRecipe(false)} />}
            <ul>
                {fakeRecipes.map((fakeRecipe,i) => <li className="recipe-item" key={i}><BriefRecipe recipe={fakeRecipe} onClick={id => expandRecipe(id)}/></li>)}
            </ul>
        </>
    );
}