import React from 'react';

export default function RecipeName({ name, setName }) {
    return (
        <div className="field">
            <label className="label" htmlFor="add-recipe-name">Namn på Rätt</label>
            <div className="control">
                <input className={`input ${name ? 'is-success' : 'is-danger'}`} type="text" id="add-recipe-name" onChange={e => setName(e.target.value)} />
            </div>
            <p className={`help ${name ? 'is-hidden' : 'is-danger'}`}>Du måste ange att namn på rätten.</p>
        </div>
    );
}