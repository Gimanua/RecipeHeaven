import React from 'react';
import Categories from './Categories';
import AddRecipeIngredients from './AddRecipeIngredients';
import AddRecipeInstructions from './AddRecipeInstructions';
import { postRecipe } from '../logic/APIHelper';
import { RecipeBuilder } from '../entities/Recipe';

export default function AddRecipe({ close }) {

    function submit() {
        if(!validateInput())
            return;
        
        const reader = new FileReader();
        reader.onloadend = () => {
            const recipe = new RecipeBuilder()
                .setName(document.getElementById('add-recipe-name').value)
                .setDescription(document.getElementById('add-recipe-description').value)
                .setUserId(6)//test
                .setCategories([{categoryId: 1}])
                .setIngredients([{"amount": 3.0, "ingredientId": 3, "name": "Ägg"}])
                .setInstructions([{"description": "Blanda runt skiten och ät.","orderIndex": 1}])
                .setImage(reader.result)
                .build();
            postRecipe(recipe);
            close();
        };
        console.log(document.getElementById('add-recipe-image').files);
        
        reader.readAsDataURL(document.getElementById('add-recipe-image').files[0]);
    }

    function validateInput(){
        const name = document.getElementById('add-recipe-name').value;
        const description = document.getElementById('add-recipe-description').value;
        const image = document.getElementById('add-recipe-image').files.length > 0;
        
        const categories = document.getElementsByClassName('category-checkbox');
        let atLeastOneCategory = false;
        for (const category of categories) {
            if(category.checked){
                atLeastOneCategory = true;
                break;
            }
        }

        const atLeastOneIngredient = document.getElementById('ingredients').hasChildNodes();
        const atLeastOneInstruction = document.getElementById('instructions').hasChildNodes();

        return name && description && image && atLeastOneCategory && atLeastOneIngredient && atLeastOneInstruction;
    }

    return (
        <form id="add-recipe-form" onSubmit={(e) => e.preventDefault()} action="/test" className="box" method="post">
            <label className="label" htmlFor="add-recipe-name">Namn på Rätt</label>
            <input className="input" type="text" id="add-recipe-name" name="title" />

            <label className="label" htmlFor="add-recipe-description">Beskrivning av Rätt</label>
            <textarea className="textarea" name="description" id="add-recipe-description" rows="6"></textarea>

            <label className="label" htmlFor="add-recipe-image">Bild på Rätt</label>
            <input type="file" accept="image/jpeg" name="image" id="add-recipe-image" />

            <Categories />

            <AddRecipeIngredients />

            <AddRecipeInstructions />

            <button onClick={() => submit()} className="has-text-weight-bold button">Lägg till Recept</button>

        </form>
    );
}