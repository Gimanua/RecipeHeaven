import React from 'react';
import Modal from './Modal';
import { InstructionBuilder } from '../entities/Instruction';

export default function RecipeInstruction({close, addInstruction, orderIndex}){
    
    const [description, setDescription] = React.useState('');

    function submit(){
        const instruction = new InstructionBuilder()
            .setDescription(description)
            .setOrderIndex(orderIndex)
            .build();
        addInstruction(instruction);
        close();
    }

    return (
        <Modal close={() => close()}>
            <div className="field">
                <label className="label" htmlFor="add-instruction-description">Instruktionsbeskrivning</label>
                <div className="control">
                    <textarea className={`textarea ${description ? 'is-success' : 'is-danger'}`} id="add-instruction-description" onChange={e => setDescription(e.target.value)}></textarea>
                </div>
                <p className={`help ${description ? 'is-hidden' : 'is-danger'}`}>Du måste ange en instruktionsbeskrivning.</p>
            </div>
            <div className="field">
                <div className="control">
                    <button className={`button ${description ? 'is-link' : 'is-hidden'}`} onClick={() => submit()}>Lägg Till</button>
                </div>
            </div>
        </Modal>
    );
}