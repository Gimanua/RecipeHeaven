import React from 'react';
import { CategoryBuilder } from '../entities/Category';

export default function Categories({ onSelectedChange }) {

    const [categoriesChecked, setCategoriesChecked] = React.useState(0);

    function onCategoryCheck(categoryId, checked) {
        setCategoriesChecked(checked ? categoriesChecked + 1 : categoriesChecked - 1);
        const category = new CategoryBuilder()
            .setCategoryId(categoryId)
            .build();
        onSelectedChange(category, checked);
    }

    return (
        <>
            <label className="label" htmlFor="categories">Kategorier</label>
            <div className="field" id="categories">
                <div className="control">
                    <label className="checkbox">
                        <input type="checkbox" value={1} onChange={(e) => onCategoryCheck(e.target.value, e.target.checked)} />
                        Vegetariskt
                    </label>
                </div>
                <div className="control">
                    <label className="checkbox">
                        <input type="checkbox" value={2} onChange={(e) => onCategoryCheck(e.target.value, e.target.checked)} />
                        Fisk
                    </label>
                </div>
                <div className="control">
                    <label className="checkbox">
                        <input type="checkbox" value={3} onChange={(e) => onCategoryCheck(e.target.value, e.target.checked)} />
                        Kött
                    </label>
                </div>
                <div className="control">
                    <label className="checkbox">
                        <input type="checkbox" value={4} onChange={(e) => onCategoryCheck(e.target.value, e.target.checked)} />
                        Efterrätt
                    </label>
                </div>
                <div className="control">
                    <label className="checkbox">
                        <input type="checkbox" value={5} onChange={(e) => onCategoryCheck(e.target.value, e.target.checked)} />
                        Varmrätt
                    </label>
                </div>
                <p className={`help ${categoriesChecked > 0 ? 'is-hidden' : 'is-danger'}`}>Du måste välja minst en kategori.</p>
            </div>
        </>
    );
}