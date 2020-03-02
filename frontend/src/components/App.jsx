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
        setCurrentContent(<Recipes />);
        break;
      default:
        setCurrentContent(<Home />);
        break;
    }
  }

  return (
    <>
      <NavigationBar currentPage={currentPage} onPageChange={(page) => switchPage(page)} />
      {currentContent}
    </>
  );
}

export default App;
