import React from 'react';

export default function RecipeImage({ selectedFile, setSelectedFile }) {
    return (
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
    );
}