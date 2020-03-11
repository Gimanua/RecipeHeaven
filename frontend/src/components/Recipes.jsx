import React from 'react';
import Filter from './Filter';
import BriefRecipe from './BriefRecipe';
import AddRecipe from './AddRecipe';
import { RecipeBuilder } from '../entities/Recipe';
import { getBriefRecipes } from '../logic/APIHelper';

/**
 * 
 * @param {Object} props The React props Object
 * @param {Boolean} props.loggedIn Whether or not the user is logged in
 * @param {Function} props.expandRecipe Callback when the user request more information about a recipe, send the id of the recipe
 */
export default function Recipes({loggedIn, expandRecipe}){

    const [showAddRecipe, setShowAddRecipe] = React.useState(false);
    const [briefRecipes, setBriefRecipes] = React.useState([]);
    const [categoryFilters, setCategoryFilters] = React.useState([]);
    React.useEffect(() => {
        getBriefRecipes()
        .then(recipes => {
            const filteredRecipes = recipes.filter(recipe => {
                const categoryIds = recipe.categories.map(category => category.categoryId);
                return categoryFilters.every(category => categoryIds.includes(category));
            });
            setBriefRecipes(filteredRecipes);
        })
    }, [categoryFilters]);

    function selectedCategoriesChanged(selectedCategory, checked){
        const categoryId = selectedCategory.categoryId;
        if (checked) {
            setCategoryFilters([...categoryFilters, categoryId])
        }
        else {
            setCategoryFilters(categoryFilters.filter(category => category !== categoryId));
        }
    }

    return (
        <>
            <Filter onCategoriesChange={selectedCategoriesChanged} />
            {loggedIn && <button onClick={() => setShowAddRecipe(true)} className="button is-fullwidth has-text-weight-semibold is-medium">+ Lägg till ett eget Recept</button>}
            {showAddRecipe && <AddRecipe close={() => setShowAddRecipe(false)} />}
            <ul>
                {briefRecipes.map((briefRecipe,i) => <li className="recipe-item" key={i}><BriefRecipe recipe={briefRecipe} onClick={id => expandRecipe(id)}/></li>)}
            </ul>
        </>
    );
}