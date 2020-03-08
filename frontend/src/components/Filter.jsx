import React from 'react';
import Categories from './Categories';
import Modal from './Modal';

export default function Filter(props) {

    const [categoriesVisible, setCategoriesVisible] = React.useState(false);

    return (
        <>
            <button className="button is-size-1 has-text-weight-bold is-fullwidth" onClick={() => setCategoriesVisible(true)}>Filtrera</button>
            {categoriesVisible && 
            <Modal close={() => setCategoriesVisible(false)}>
                <Categories onSelectedChange={(value, checked) => console.log(value, checked)}/>
            </Modal>}
        </>
    );
}