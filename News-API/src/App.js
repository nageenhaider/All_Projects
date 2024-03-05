import {  Route, Routes } from 'react-router';
import React from 'react'
import { Navbar } from './Components/Navbar';
import Home from'./pages/Home'
import  Categorypage  from './pages/Categorypage';
import Searchpage from './pages/Searchpage';
import { useParams } from 'react-router-dom';
function  App () {
  const SearchpageRoute = () => {
    const { wrd } = useParams();
  
    return <Searchpage wrd={wrd} />;
  };
  return (
    <div>
      <Navbar />
      <Routes>
         <Route path='/' element={<Home />} />
         <Route path='/general' element={<Categorypage cat="general" />} />
         <Route path='/business' element={<Categorypage cat="business" />} />
         <Route path='/technology' element={<Categorypage cat="technology" />} />
         <Route path='/entertainment' element={<Categorypage cat="entertainment" />} />
         <Route path='/politics' element={<Categorypage cat="politics" />} />
         <Route path='/sports' element={<Categorypage cat="sports" />} />
         <Route path='/health' element={<Categorypage cat="health" />} />
         <Route path='/search/:wrd' element={<SearchpageRoute />} />
      </Routes>
    </div>
  );
}

export default App