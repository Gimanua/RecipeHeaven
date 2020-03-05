import React from 'react';
import Filter from './Filter';
import BriefRecipe from './BriefRecipe';
import './styling/Recipes.scss';
import AddRecipe from './AddRecipe';

const fakeRecipes = [
    <BriefRecipe image="https://cdn.pixabay.com/photo/2017/01/30/13/49/pancakes-2020863_960_720.jpg" name="Pannkakor" description="Enkelt att laga och gott att äta, med detta recept så blir din lunch eller middag eller fika en enkel match. Rätten kräver oerhört lite från kocken och kylen och såklart plånboken." />,
    <BriefRecipe image="https://cdn.pixabay.com/photo/2017/01/30/13/49/pancakes-2020863_960_720.jpg" name="Pannkakor" description="Enkelt att laga och gott att äta, med detta recept så blir din lunch eller middag eller fika en enkel match. Rätten kräver oerhört lite från kocken och kylen och såklart plånboken." />
];

/**
 * 
 * @param {Object} props The React props Object
 * @param {Boolean} props.loggedIn Whether or not the user is logged in
 */
export default function Recipes({loggedIn}){

    const [showAddRecipe, setShowAddRecipe] = React.useState(false);

    return (
        <>
            <Filter />
            {loggedIn && <><br /><button onClick={() => setShowAddRecipe(true)} className="button">Lägg till ett eget Recept</button></>}
            {showAddRecipe && <AddRecipe />}
            <ul>
                {fakeRecipes.map((v,i) => <li className="recipe-item" key={i}>{v}</li>)}
            </ul>
        </>
    );
}