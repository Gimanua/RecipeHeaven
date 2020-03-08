import React from 'react';
import RecipeInstruction from './RecipeInstruction';

export default function Instructions({instructions, addInstruction}){
    
    const [showAddInstruction, setShowAddInstruction] = React.useState(false);
    
    return (
        <>
            <label className="label" htmlFor="instructions">Instruktioner</label>
            <div className="field">
                <div className="control">
                    <button className={`button ${instructions.length > 0 ? 'is-success' : 'is-danger'}`} onClick={() => setShowAddInstruction(true)}>Lägg till Instruktion</button>
                </div>
                <p className={`help ${instructions.length > 0 ? 'is-hidden' : 'is-danger'}`}>Du måste ange minst en instruktion.</p>
            </div>
            {showAddInstruction && <RecipeInstruction orderIndex={instructions.length+1} close={() => setShowAddInstruction(false)} addInstruction={instruction => addInstruction(instruction)} />}
            <div className="content">
                <ol id="instructions">
                    {instructions.map((instruction, index) => <li key={index}>{instruction.description}</li>)}
                </ol>
            </div>
        </>
    );
}