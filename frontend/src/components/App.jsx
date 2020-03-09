import React from 'react';
import Home from './Home';
import './styling/App.scss';
import NavigationBar from './NavigationBar';
import Recipes from './Recipes';
import { getRecipe } from '../logic/APIHelper';
import Recipe from './Recipe';

function App() {

  const [currentContent, setCurrentContent] = React.useState(<Home />);
  const [currentPage, setCurrentPage] = React.useState('home');

  function switchPage(newPage) {
    setCurrentPage(newPage);
    switch (newPage) {
      case 'home':
        setCurrentContent(<Home />);
        break;
      case 'recipes':
        setCurrentContent(<Recipes loggedIn={true} expandRecipe={(id) => getRecipe(id).then(recipe => setCurrentContent(<Recipe loggedIn={true} recipe={recipe} />))} />);
        break;
      default:
        setCurrentContent(<Home />);
        break;
    }
  }

  return (
    <>
      <NavigationBar currentPage={currentPage} onPageChange={(page) => switchPage(page)} />
      <main className="container">
        {currentContent}
      </main>
    </>
  );
}

export default App;
