import React from 'react';

export default function Ingredients({ onChange }) {

    const [ingredients, setIngredients] = React.useState([]);
    const [showModal, setShowModal] = React.useState(false);

    function addIngredient() {
        const name = document.getElementById('add-ingredient-name').value;
        const amount = document.getElementById('add-ingredient-amount').value;
        const unit = document.getElementById('add-ingredient-unit').value;
        const ingredient = { name: name, amount: amount, unit: unit };
        //setIngredients([...ingredients, ingredient]);
        setShowModal(false);
    }

    return (
        <>
            <label className="label" htmlFor="ingredients">Ingredienser</label>
            <div className="field" id="ingredients">
                <div className="control">
                    <button className={`button ${ingredients.length > 0 ? 'is-success' : 'is-danger'}`} onClick={() => setShowModal(true)}>Lägg till Ingrediens</button>
                </div>
                <p className={`help ${ingredients.length > 0 ? 'is-hidden' : 'is-danger'}`}>Du måste ange minst en ingrediens.</p>
                <div className="content">
                    <ul>
                        {ingredients.map((ingredient, index) => <li key={index}>{ingredient.name}</li>)}
                    </ul>
                </div>
            </div>

            <div className={`modal${(showModal && ' is-active') || ''}`}>
                <div className="modal-background"></div>
                <div className="modal-content box">
                    <label className="label" htmlFor="add-ingredient-name">
                        <input list="add-ingredient-list" id="add-ingredient-name" />
                        Namn på ingrediens
                    </label>

                    <datalist id="add-ingredient-list">
                        <option value="Ägg" />
                        <option value="Mjölk" />
                        <option value="Salt" />
                        <option value="Smör" />
                        <option value="Vetemjöl" />
                    </datalist><br />
                    <label className="label" htmlFor="add-ingredient-amount">Mängd</label>
                    <input id="add-ingredient-amount" defaultValue="1" min="0.1" step="0.1" type="number" /><br />
                    <label className="label" htmlFor="add-ingredient-unit">Enhet</label>
                    <input list="add-ingredient-unit-list" id="add-ingredient-unit" />
                    <datalist id="add-ingredient-unit-list">
                        <option value="st" />
                        <option value="kg" />
                        <option value="hg" />
                        <option value="l" />
                        <option value="dl" />
                        <option value="cl" />
                        <option value="ml" />
                        <option value="msk" />
                        <option value="tsk" />
                        <option value="krm" />
                        <option value="kkp" />
                        <option value="glas" />
                    </datalist><br />
                    <button className="button" onClick={() => addIngredient()}>Lägg Till</button>
                </div>
                <button onClick={() => setShowModal(false)} className="modal-close is-large" aria-label="close"></button>
            </div>
            <ul id="ingredients">
                {ingredients}
            </ul>
        </>
    );
}