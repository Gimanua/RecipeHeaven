import React from 'react';
import { IngredientBuilder } from '../entities/Ingredient';
import Modal from './Modal';

export default function RecipeIngredient({ close, addIngredient }) {

    const [ingredientId, setIngredientId] = React.useState(0);
    const [amount, setAmount] = React.useState(0);
    const [unitId, setUnitId] = React.useState(0);

    function submit() {
        const ingredient = new IngredientBuilder()
            .setIngredientId(ingredientId)
            .setAmount(amount)
            .setUnitId(unitId)
            .build();
        addIngredient(ingredient);
        close();
    }

    return (
        <Modal close={() => close()}>
            <div className="field">
                <label className="label" htmlFor="add-ingredient-name">Namn på ingrediens</label>
                <div className="control">
                    <div className={`select ${ingredientId ? 'is-success' : 'is-danger'}`}>
                        <select id="add-ingredient-name" defaultValue={0} onChange={e => setIngredientId(parseFloat(e.target.value))}>
                            <option value={0} className="is-hidden" disabled></option>
                            <option value={1}>Ägg</option>
                            <option value={2}>Mjölk</option>
                            <option value={3}>Salt</option>
                            <option value={4}>Smör</option>
                            <option value={5}>Vetemjöl</option>
                        </select>
                    </div>
                </div>
                <p className={`help ${ingredientId ? 'is-hidden' : 'is-danger'}`}>Du måste välja ingrediens.</p>
            </div>

            <div className="field">
                <label className="label" htmlFor="add-ingredient-amount">Mängd</label>
                <div className="control">
                    <input className={`input ${amount ? 'is-success' : 'is-danger'}`} id="add-ingredient-amount" min="0.1" max="1000" step="0.1" type="number" onChange={e => setAmount(e.target.valueAsNumber)} />
                </div>
                <p className={`help ${amount ? 'is-hidden' : 'is-danger'}`}>Du måste ange en mängd.</p>
            </div>

            <div className="field">
                <label className="label" htmlFor="add-ingredient-unit">Enhet</label>
                <div className="control">
                    <div className={`select ${unitId ? 'is-success' : 'is-danger'}`}>
                        <select id="add-ingredient-unit" defaultValue={0} onChange={e => setUnitId(parseFloat(e.target.value))}>
                            <option value={0} className="is-hidden" disabled></option>
                            <option value={1}>st</option>
                            <option value={2}>kg</option>
                            <option value={3}>hg</option>
                            <option value={4}>l</option>
                            <option value={5}>dl</option>
                            <option value={6}>cl</option>
                            <option value={7}>ml</option>
                            <option value={8}>msk</option>
                            <option value={9}>tsk</option>
                            <option value={10}>krm</option>
                            <option value={11}>kkp</option>
                            <option value={12}>glas</option>
                        </select>
                    </div>
                </div>
                <p className={`help ${unitId ? 'is-hidden' : 'is-danger'}`}>Du måste ange en enhet.</p>
            </div>
            <div className="field">
                <div className="control">
                    <button className={`button ${(ingredientId && amount && unitId) ? 'is-link' : 'is-hidden'}`} onClick={() => submit()}>Lägg Till</button>
                </div>
            </div>
        </Modal>
    );
}