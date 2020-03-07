import React from 'react';

export default function RecipeDescription({ description, setDescription }) {
    return (
        <div className="field">
            <label className="label" htmlFor="add-recipe-description">Beskrivning av Rätt</label>
            <div className="control">
                <textarea className={`textarea ${description ? 'is-success' : 'is-danger'}`} id="add-recipe-description" onChange={e => setDescription(e.target.value)}></textarea>
            </div>
            <p className={`help ${description ? 'is-hidden' : 'is-danger'}`}>Du måste ange en beskrivning på rätten.</p>
        </div>
    );
}