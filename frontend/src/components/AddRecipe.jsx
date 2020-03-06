import React from 'react';
import Categories from './Categories';
import Ingredients from './Ingredients';
import AddRecipeInstructions from './AddRecipeInstructions';
import { postRecipe } from '../logic/APIHelper';
import { RecipeBuilder } from '../entities/Recipe';
import { loadImageAsBase64 } from '../logic/ImageHelper';

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
            console.log(imageAsBase64);
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
            <div className="field">
                <label className="label" htmlFor="add-recipe-name">Namn på Rätt</label>
                <div className="control">
                    <input className={`input ${name ? 'is-success' : 'is-danger'}`} type="text" id="add-recipe-name" onChange={e => setName(e.target.value)} />
                </div>
                <p className={`help ${name ? 'is-hidden' : 'is-danger'}`}>Du måste ange att namn på rätten.</p>
            </div>

            <div className="field">
                <label className="label" htmlFor="add-recipe-description">Beskrivning av Rätt</label>
                <div className="control">
                    <textarea className={`textarea ${description ? 'is-success' : 'is-danger'}`} id="add-recipe-description" onChange={e => setDescription(e.target.value)}></textarea>
                </div>
                <p className={`help ${description ? 'is-hidden' : 'is-danger'}`}>Du måste ange en på rätten.</p>
            </div>

            <div className="field">
                <label className="label" htmlFor="add-recipe-image">Bild på Rätt</label>
                <div className="control">
                    <div className={`file ${selectedFile ? 'is-success' : 'is-danger'}`}>
                        <label className="file-label">
                            <input className="file-input" id="add-recipe-image" type="file" accept="image/jpeg" onChange={e => { if (e.target.files.length === 1) setSelectedFile(e.target.files[0]) }} />
                            <span className="file-cta">
                                <span className="file-icon">
                                    <i className="fas fa-upload"></i>
                                </span>
                                <span className="file-label">Välj en Fil...</span>
                            </span>
                            <span className="file-name">{(selectedFile && selectedFile.name || 'Ingen fil vald')}</span>
                        </label>
                    </div>
                </div>
                <p className={`help ${selectedFile ? 'is-hidden' : 'is-danger'}`}>Du måste ladda up en bild på rätten.</p>
            </div>

            <Categories onSelectedChange={selectedCategoryChange} />

            <Ingredients />

            <AddRecipeInstructions />

            <button onClick={() => submit()} className="has-text-weight-bold button is-link">Lägg till Recept</button>

        </form>
    );
}