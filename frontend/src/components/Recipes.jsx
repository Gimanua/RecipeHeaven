import React from 'react';
import Filter from './Filter';
import BriefRecipe from './BriefRecipe';

const fakeRecipes = [
    <BriefRecipe image="pannkakor.png" name="Pannkakor" description="Enkelt att laga och gott att äta, med detta recept så blir din lunch eller middag eller fika en enkel match. Rätten kräver oerhört lite från kocken och kylen och såklart plånboken." />,
    <BriefRecipe image="pannkakor.png" name="Pannkakor" description="Enkelt att laga och gott att äta, med detta recept så blir din lunch eller middag eller fika en enkel match. Rätten kräver oerhört lite från kocken och kylen och såklart plånboken." />
];

/**
 * 
 * @param {Object} props The React props Object. Here it doesn't do anything, it's there to clarify that it's a React component.
 */
export default function Recipes(props){
    return (
        <>
            <Filter />
            {fakeRecipes}
        </>
    );
}