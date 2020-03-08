import React from 'react';

export default function Modal({close, children}){
    return (
        <div className="modal is-active">
            <div className="modal-background"></div>
            <div className="modal-content box">
                {children}
            </div>
            <button className="modal-close is-large" aria-label="close" onClick={() => close()}></button>
        </div>
    );
}