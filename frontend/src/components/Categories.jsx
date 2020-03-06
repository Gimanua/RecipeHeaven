import React from 'react';

export default function Categories({ onSelectedChange }) {

    const [categoriesChecked, setCategoriesChecked] = React.useState(0);

    function onCategoryCheck(category, checked) {
        setCategoriesChecked(checked ? categoriesChecked + 1 : categoriesChecked - 1);
        onSelectedChange(category, checked);
    }

    return (
        <>
            <label className="label" htmlFor="categories">Kategorier</label>
            <div className="field" id="categories">
                <div className="control">
                    <label className="checkbox">
                        <input type="checkbox" value="vegetarian" onChange={(e) => onCategoryCheck(e.target.value, e.target.checked)} />
                        Vegetariskt
                    </label>
                </div>
                <div className="control">
                    <label className="checkbox">
                        <input type="checkbox" value="fish" onChange={(e) => onCategoryCheck(e.target.value, e.target.checked)} />
                        Fisk
                    </label>
                </div>
                <div className="control">
                    <label className="checkbox">
                        <input type="checkbox" value="meat" onChange={(e) => onCategoryCheck(e.target.value, e.target.checked)} />
                        Kött
                    </label>
                </div>
                <div className="control">
                    <label className="checkbox">
                        <input type="checkbox" value="dessert" onChange={(e) => onCategoryCheck(e.target.value, e.target.checked)} />
                        Efterrätt
                    </label>
                </div>
                <div className="control">
                    <label className="checkbox">
                        <input type="checkbox" value="main" onChange={(e) => onCategoryCheck(e.target.value, e.target.checked)} />
                        Varmrätt
                    </label>
                </div>
                <p className={`help ${categoriesChecked > 0 ? 'is-hidden' : 'is-danger'}`}>Du måste välja minst en kategori.</p>
            </div>
        </>
    );
}