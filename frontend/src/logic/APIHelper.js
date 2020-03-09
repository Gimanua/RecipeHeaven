import { RecipeBuilder, Recipe } from '../entities/Recipe';

export const CLIENT_ID = '05eebad14695aff4984a';
export const GITHUB_OAUTH_VERIFY = 'https://github.com/login/oauth/authorize';

const fakeRecipes = [
    new RecipeBuilder().setId(1).setImage('https://cdn.pixabay.com/photo/2017/01/30/13/49/pancakes-2020863_960_720.jpg').setName('Pannkakor').setDescription('Enkelt att laga och gott att äta, med detta recept så blir din lunch eller middag eller fika en enkel match. Rätten kräver oerhört lite från kocken och kylen och såklart plånboken.').build(),
    new RecipeBuilder().setId(2).setImage('https://cdn.pixabay.com/photo/2017/01/30/13/49/pancakes-2020863_960_720.jpg').setName('Pannkakor').setDescription('Enkelt att laga och gott att äta, med detta recept så blir din lunch eller middag eller fika en enkel match. Rätten kräver oerhört lite från kocken och kylen och såklart plånboken.').build()
];

export function navigateToGitHub(){
    window.location.replace(`https://github.com/login/oauth/authorize?client_id=${CLIENT_ID}`);
}

export function getCode(){
    const searchParams = new URLSearchParams(window.location.search);
    return searchParams.get('code');
}

export function removeCode(){
    window.history.replaceState({}, document.title, './');
}

export function hasCode(){
    const searchParams = new URLSearchParams(window.location.search);
    return searchParams.has('code');
}

export function saveToken(token){
    localStorage.setItem('token', token);
}

export function clearToken(){
    localStorage.removeItem('token');
}

export async function getTokenFromBackend(code){
    try {
        const response = await fetch(`./api/token/${code}`);
        if(response.ok){
            return response.headers.get('token');
        }
        else{
            console.log(`Server responded with ${response.statusText}`)
        }
    } catch (error) {
        console.log(`Failed to get token: ${error}`);
    }
    throw new Error();
}

export function hasToken(){
    return localStorage.getItem('token');
}

/**
 * Gets a recipe with a certain id.
 * @param {Number} id The id of the recipe.
 * @returns {Promise<Recipe>} The recipe.
 */
export async function getRecipe(id){
    try {
        const response = await fetch(`./api/recipe/${id}`);
        if(response.ok){
            const jsonRecipe = await response.json();
            return jsonRecipe;
        }
        else{
            console.log(`Server responded with: ${response.statusText}`);
        }
    } catch (error) {
        console.log(`Failed to get recipe with id ${id}: ${error}`);
    }
}

/**
 * Gets brief information of 8 recipes.
 * @returns {Promise<Array<Recipe>>} The recipes
 */
export async function getBriefRecipes(){
    const recipes = [];
    try {
        const response = await fetch('./api/brief-recipes/8');
        if(response.ok){
            const jsonRecipes = await response.json();
            jsonRecipes.forEach(jsonRecipe => {
                const recipe = new RecipeBuilder()
                    .setId(jsonRecipe.id)
                    .setName(jsonRecipe.name)
                    .setImage(jsonRecipe.image)
                    .setDescription(jsonRecipe.description)
                    .setLikes(jsonRecipe.likes)
                    .setPosterUsername(jsonRecipe.posterUsername)
                    .build();
                recipes.push(recipe);
            });
        }
        else{
            console.log(`Server responded with status ${response.statusText}`);
        }
    } catch (error) {
        console.log(`Failed to get brief recipes: ${error}`);
    }
    return recipes;
}

/**
 * Posts a recipe to the backend
 * @param {import('../entities/Recipe').Recipe} recipe The recipe to post
 */
export async function postRecipe(recipe) {
    try{
        recipe.userId = 1;//Tillfällig lösning
        console.log(recipe);
        const response = await fetch('./api/recipe', {
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
