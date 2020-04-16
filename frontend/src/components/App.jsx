import React from 'react';
import Home from './Home';
import './styling/App.scss';
import NavigationBar from './NavigationBar';
import Recipes from './Recipes';
import { getRecipe, hasToken, hasCode, getTokenFromBackend, saveToken, getCode, removeCode, clearToken, navigateToGitHub, login } from '../logic/APIHelper';
import Recipe from './Recipe';

function App() {
  //Detta är ett anti-pattern. Det är dåligt att spara faktiskta react-komponenter i state.
  //Anledningen är att props som du skickar in fixeras vid tillfället då du lägger in 
  //komponenten i state, och det riskerar då att hamna i osynk när denna komponent renderas om
  //Som du märker så funkar det ofta, men kan leda som sagt leda till buggar där komponeter inte
  //uppdateras som man förväntar sig...
  const [currentContent, setCurrentContent] = React.useState(<Home />);

  //...bättre att använda state som detta för att avgöra vad som skall renderas
  const [currentPage, setCurrentPage] = React.useState('home');
  const [loggedIn, setLoggedIn] = React.useState(hasToken());

  if (hasCode()) {
    getTokenFromBackend(getCode())
      .then(token => {
        saveToken(token);
        removeCode();
        return login(token);
      })
      .then(loggedIn => {
        if (loggedIn) {
          setLoggedIn(true);
          alert('You are now logged in!');
        }
        else {
          alert('Failed to log in!');
        }
      })
      .catch(error => console.log(error));
  }

  function switchPage(newPage) {
    setCurrentPage(newPage);
    switch (newPage) {
      case 'home':
        setCurrentContent(<Home />);
        break;
      case 'recipes':
        setCurrentContent(<Recipes loggedIn={loggedIn} expandRecipe={(id) => getRecipe(id).then(recipe => setCurrentContent(<Recipe loggedIn={loggedIn} recipe={recipe} />))} />);
        break;
      default:
        setCurrentContent(<Home />);
        break;
    }
  }

  function logIn() {
    navigateToGitHub();
  }

  function logOut() {
    clearToken();
    setLoggedIn(false);
  }

  return (
    <>
      <NavigationBar currentPage={currentPage} onPageChange={(page) => switchPage(page)} loggedIn={loggedIn} logIn={() => logIn()} logOut={() => logOut()} />
      <main className="container">
        {currentContent}
      </main>
    </>
  );
}

export default App;
