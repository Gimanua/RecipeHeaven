import React from 'react';
import Home from './Home';
import './styling/App.scss';
import NavigationBar from './NavigationBar';
import Recipes from './Recipes';
import { getRecipe, hasToken, GITHUB_OAUTH_VERIFY, CLIENT_ID, hasCode, getTokenFromBackend, saveToken, getCode, removeCode, clearToken, navigateToGitHub } from '../logic/APIHelper';
import Recipe from './Recipe';

function App() {

  const [currentContent, setCurrentContent] = React.useState(<Home />);
  const [currentPage, setCurrentPage] = React.useState('home');
  const [loggedIn, setLoggedIn] = React.useState(hasToken());

  if (hasCode()) {
    getTokenFromBackend(getCode())
      .then(token => {
        saveToken(token);
        removeCode();
        setLoggedIn(true);
        alert('You are now logged in!');
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

  function logIn(){
    navigateToGitHub();
  }

  function logOut(){
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
