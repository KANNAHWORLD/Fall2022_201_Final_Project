import React from 'react';
import Navbar from './components/Navbar';
import './App.css';
import LogIn from './pages/LogIn';
import Match from './pages/Match';
import Profile from './pages/Profile';
import Questionnaire from './pages/Questionnaire';
import SignUp from './pages/SignUp';
import {BrowserRouter, Routes, Route } from 'react-router-dom';

function App() {
  return (
    <>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path='/' element={<LogIn/>} />
          <Route path='/login' element={<LogIn/>} />
          <Route path='/match' element={<Match/>} />
          <Route path='/profile' element={<Profile/>} />
          <Route path='/questionnaire' element={<Questionnaire/>} />
          <Route path='/signup' element={<SignUp/>} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;

