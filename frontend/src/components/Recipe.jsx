import React from 'react';
import { postLike, getToken, postReport } from '../logic/APIHelper';

/**
 * 
 * @param {Object} props The react props object
 * @param {Boolean} props.loggedIn If the user is logged in
 * @param {import('../entities/Recipe').Recipe} props.recipe The recipe to show
 */
export default function Recipe({ loggedIn, recipe }) {
    return (
        <div className="card has-text-centered">
            <div className="card-header">
                <h2 className="card-header-title">
                    {recipe.name}
                    {loggedIn && <span className="icon"><i className="fas fa-edit"></i></span>}
                    {loggedIn && <span className="icon"><i className="fas fa-trash-alt"></i></span>}
                </h2>
            </div>
            <div className="card-content">
                <p>{`Receptet är inlagt av användaren: ${recipe.posterUsername}`}</p>
                <img src={recipe.image} alt={recipe.name} />
                <p>{recipe.description}</p>
                <h3 className="subtitle is-2">Kategorier</h3>
                <div className="content">
                    <ul className="is-inline-block has-text-left">
                        {recipe.categories.map((category, i) => <li key={i}>{category.name}</li>)}
                    </ul>
                </div>
                <h3 className="subtitle is-2">Ingredienser</h3>
                <div className="content">
                    <ul className="is-inline-block has-text-left">
                        {recipe.ingredients.map((ingredient, i) => <li key={i}>{`${ingredient.amount}${ingredient.unitAbbreviation || 'st'} ${ingredient.name}`}</li>)}
                    </ul>
                </div>
                <h3 className="subtitle is-2">Tillagning</h3>
                <div className="content">
                    <ol className="is-inline-block has-text-left">
                        {recipe.instructions.map((instruction, i) => <li key={i}>{instruction.description}</li>)}
                    </ol>
                </div>
                {loggedIn && <button className="button is-large" onClick={() => postLike(recipe.id, getToken())}><i className="fas fa-smile"></i>Gilla</button>}
                {loggedIn && <button className="button is-large" onClick={() => postReport(recipe.id, getToken())}><i className="fas fa-angry"></i>Rapportera olämpligt recept</button>}
                <h3 className="subtitle is-2">Kommentarer</h3>
                <ul>
                    {recipe.comments.map((comment, i) =>
                        <li className="media box" key={i}>
                            <div className="media-content">
                                <p>{comment.comment}</p>
                                <div className="level">
                                    <div className="level-left">
                                        <div className="level-item">
                                            {loggedIn && <button className="button">Svara</button>}
                                        </div>
                                    </div>
                                    <div className="level-right">
                                        <div className="level-item">
                                            <p>{comment.posterUsername}</p>
                                        </div>
                                    </div>
                                </div>
                                {comment.replies &&
                                    <ul>
                                        {comment.replies.map((reply, i) =>
                                            <li className="media box" key={i}>
                                                <div className="media-content">
                                                    <p className="is-block">{reply.reply}</p>
                                                    <div className="level">
                                                        <div className="level-left"></div>
                                                        <div className="level-right">
                                                            <div className="level-item">
                                                                <p>{reply.posterUsername}</p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        )}
                                    </ul>
                                }
                            </div>
                        </li>
                    )}
                </ul>
            </div>
        </div>
    );
}