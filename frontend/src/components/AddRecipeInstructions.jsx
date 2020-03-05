import React from 'react';

export default function AddRecipeInstructions(props) {

    const [showModal, setShowModal] = React.useState(false);
    const [instructions, setInstructions] = React.useState([]);

    let modalClasses = 'modal';
    if(showModal){
        modalClasses += ' is-active';
    }

    function addInstruction(){
        const instruction = document.getElementById('add-instruction-description').value;
        setInstructions([...instructions, instruction]);
        setShowModal(false);
    }

    return (
        <>
            <h2 className="title">Instruktioner</h2>
            <button onClick={() => setShowModal(true)} className="button">Lägg till Instruktion</button>
            <div className={modalClasses}>
                <div className="modal-background"></div>
                <div className="modal-content box">
                    <label className="label" htmlFor="add-instruction-description">Instruktionsbeskrivning</label>
                    <textarea className="textarea" id="add-instruction-description" rows="3"></textarea>
                    <button onClick={() => addInstruction()} className="button">Lägg Till</button>
                </div>
                <button onClick={() => setShowModal(false)} className="modal-close is-large" aria-label="close"></button>
            </div>
            <ol>
                {instructions.map((instruction, index) => <li key={index}>{instruction}</li>)}
            </ol>
        </>
    );
}