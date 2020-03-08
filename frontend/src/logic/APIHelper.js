import { RecipeBuilder, Recipe } from "../entities/Recipe";

/**
 * Posts a recipe to the backend
 * @param {Recipe} recipe The recipe to post
 */
export async function postRecipe(recipe) {
    try{
        recipe.userId = 1;//Tillfällig lösning
        console.log(recipe);
        const response = await fetch('/recipe', {
            method: 'POST',
            headers: {
                token: '91ef227ec4374a488c720dafcd7949cef3ee7738',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(recipe)
        });
        if (response.status === 201) {
            const json = await response.json();
            console.log('Succeeded in posting recipe');
            console.log(json);
        }
        else {
            console.log('Server responded with failure.');
        }
    } catch(error){
        console.log(`Failed to send recipe: ${error}`);
    }
}
