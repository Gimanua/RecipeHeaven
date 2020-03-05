import React from 'react';
import Categories from './Categories';

export default function Filter(props) {

    const [categoriesVisible, setCategoriesVisible] = React.useState(false);
    
    let categoriesClasses = 'modal';
    if(categoriesVisible){
        categoriesClasses += ' is-active';
    }

    return (
        <>
            <button className="button is-size-1 has-text-weight-bold is-fullwidth" onClick={() => setCategoriesVisible(true)}>Filtrera</button>
            <div className={categoriesClasses}>
                <div className="modal-background"></div>
                <div className="modal-content">
                    <Categories />
                </div>
                <button onClick={() => setCategoriesVisible(false)} className="modal-close is-large" aria-label="close"></button>
            </div>
        </>
    );
}