import React from 'react';
import Home from './Home';
import './styling/App.scss';
import NavigationBar from './NavigationBar';
import Recipes from './Recipes';

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
        setCurrentContent(<Recipes expandRecipe={(id) => setCurrentContent(<p className="box">{`Här ska information om recept med id ${id} stå.`}</p>)} loggedIn={true} />);
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
