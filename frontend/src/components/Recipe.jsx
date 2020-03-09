import React from 'react';

/**
 * 
 * @param {Object} props The react props object
 * @param {Boolean} props.loggedIn If the user is logged in
 * @param {import('../entities/Recipe').Recipe} props.recipe The recipe to show
 */
export default function Recipe({ loggedIn, recipe }) {
    console.log(recipe.comments)
    return (
        <div className="card has-text-centered">
            <div className="card-header">
                <h2 className="card-header-title">{recipe.name}</h2>
            </div>
            <div className="card-content">
                <p>{`Receptet är inlagt av användaren: ${recipe.posterUsername}`}</p>
                <img src={recipe.image} alt={recipe.name} />
                <p>{recipe.description}</p>
                <h3 className="subtitle">Kategorier</h3>
                <ul className="content">
                    {recipe.categories.map((category, i) => <li key={i}>{category.name}</li>)}
                </ul>
                <h3 className="subtitle">Ingredienser</h3>
                <ul className="content">
                    {recipe.ingredients.map((ingredient, i) => <li key={i}>{`${ingredient.amount}${ingredient.unitAbbreviation || 'st'} ${ingredient.name}`}</li>)}
                </ul>
                <h3 className="subtitle">Tillagning</h3>
                <ol className="content">
                    {recipe.instructions.map((instruction, i) => <li key={i}>{instruction.description}</li>)}
                </ol>
                <button className="button">Gilla</button>
                <button className="button">Rapportera olämpligt recept</button>
                <h3 className="subtitle">Kommentarer</h3>
                <ul>
                    {recipe.comments.map((comment, i) =>
                        <li className="media box" key={i}>
                            <div className="media-content">
                                <p>{comment.comment}</p>
                                <div className="level">
                                    <div className="level-left">
                                        <div className="level-item">
                                            <button className="button">Svara</button>
                                        </div>
                                    </div>
                                    <div className="level-right">
                                        <div className="level-item">
                                            <p>{comment.posterUsername}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            {comment.replies && 
                            <ul>
                                {comment.replies.map((reply, i) => 
                                    <li className="media box" key={i}>
                                        <p>{reply.reply}</p>
                                        <div className="level">
                                            <div className="level-right">
                                                <div className="level-item">
                                                    <p>{reply.posterUsername}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                )}
                            </ul>
                            }
                        </li>
                    )}
                </ul>
            </div>
        </div>
    );
}