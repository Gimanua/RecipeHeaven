import React from 'react';
import Categories from './Categories';
import Ingredients from './Ingredients';
import { postRecipe } from '../logic/APIHelper';
import { RecipeBuilder } from '../entities/Recipe';
import { loadImageAsBase64 } from '../logic/ImageHelper';
import Name from './RecipeName';
import Description from './RecipeDescription';
import Image from './RecipeImage';
import Instructions from './Instructions';

export default function AddRecipe({ close }) {

    const [name, setName] = React.useState('');
    const [description, setDescription] = React.useState('');
    const [selectedFile, setSelectedFile] = React.useState(null);
    const [categories, setCategories] = React.useState([]);
    const [ingredients, setIngredients] = React.useState([]);
    const [instructions, setInstructions] = React.useState([]);

    async function submit() {
        try {
            const imageAsBase64 = await loadImageAsBase64(selectedFile);
            const recipeBuilder = new RecipeBuilder()
                .setName(name)
                .setDescription(description)
                .setImage(imageAsBase64)
                .setCategories(categories)
                .setIngredients(ingredients)
                .setInstructions(instructions);
            postRecipe(recipeBuilder.build());
            close();
        } catch (error) {
            console.log('Failed to load image:');
            console.log(error);
        }
    }

    function selectedCategoryChange(selectedCategory, checked) {
        if (checked) {
            setCategories([...categories, selectedCategory])
        }
        else {
            setCategories(categories.filter(category => category !== selectedCategory));
        }
    }

    return (
        <form className="box" onSubmit={(e) => e.preventDefault()}>

            <Name name={name} setName={setName} />

            <Description description={description} setDescription={setDescription} />

            <Image selectedFile={selectedFile} setSelectedFile={setSelectedFile} />

            <Categories required={true} onSelectedChange={selectedCategoryChange} />

            <Ingredients ingredients={ingredients} addIngredient={ingredient => setIngredients([...ingredients, ingredient])} />

            <Instructions instructions={instructions} addInstruction={instruction => setInstructions([...instructions, instruction])} />

            <div className="field is-grouped">
                <div className={`control${name && description && selectedFile && categories.length > 0 && ingredients.length > 0 && instructions.length > 0 ? '' : ' is-hidden'}`}>
                    <button onClick={() => submit()} className="button is-primary">Lägg till Recept</button>
                </div>
                <div className="control">
                    <button onClick={() => close()} className="button is-link is-light">Avbryt</button>
                </div>
            </div>
        </form>
    );
}