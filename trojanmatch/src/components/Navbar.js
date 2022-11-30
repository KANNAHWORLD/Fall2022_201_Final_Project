import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css';
import logo from '../images/logo.png';
import "@fontsource/comfortaa";

function Navbar() {
    const [click, setClick] = useState(false);
    const handleClick = () => setClick(!click);
  
    // for toggle b/w resstaurants and customers
    const [buttonText, setButtonText] = useState((window.location.pathname === "/login" || window.location.pathname === "/signup" || window.location.pathname === "/") ? "Log In" : "Find Your Match!");
  
    // for button of joining waitlist/become partner
    const [buttonText1, setButtonText1] = useState((window.location.pathname === "/login" || window.location.pathname === "/signup"|| window.location.pathname === "/") ? "Sign Up" : "Questionnaire");


    const [buttonText2, setButtonText2] = useState((window.location.pathname === "/login" || window.location.pathname === "/signup"|| window.location.pathname === "/") ? "" : "Profile");

    useEffect(()=> {
        if (window.location.pathname === "/login" || window.location.pathname === "/signup" || window.location.pathname === "/"){
            setButtonText("Log In");
            setButtonText1("Sign Up");
            setButtonText2("");
        }
        else{
            setButtonText("Find Your Match!");
            setButtonText1("Questionnaire");
            setButtonText2("Profile");
        }
    })
  
    // NEW CHANG
  
    return (
      <>
     
        <nav className='navbar'>
        <div className='navbar-logo'>
              <img className='logo1' src={logo}/>
            </div>
          <div className='navbar-container'>
            
            <p className={click ? 'name-active' : 'name'}>Trojan Match</p>
            <div className='menu-icon' onClick={handleClick}>
              <i className={click ? 'fas fa-times' : 'fas fa-bars'} />
            </div>
            <ul className={click ? 'nav-menu active' : 'nav-menu'}>
              <li className='nav-item'> 
                <Link to={buttonText2==='' ? '/login' : '/profile'} className={'nav-links'} onClick={() => {window.scrollTo({top: 0, left: 0, behavior:'auto'});}}>
                  {buttonText2}
                </Link>
              </li>
            </ul>
            <ul className={click ? 'nav-menu active' : 'nav-menu'}>
              <li className='nav-item'> 
                <Link to={buttonText1==='Sign Up' ? '/signup' : '/questionnaire'} className={'nav-links'} onClick={() => { window.scrollTo({top: 0, left: 0, behavior:'auto'});}}>
                  {buttonText1}
                </Link>
              </li>
            </ul>
            <ul className={click ? 'nav-menu active' : 'nav-menu'}>
              <li className='nav-item'> 
                <Link to={buttonText==='Log In' ? '/login' : '/match'} className={'nav-links'} onClick={() => {window.scrollTo({top: 0, left: 0, behavior:'auto'});}}>
                  {buttonText}
                </Link>
              </li>
            </ul>
          </div>
        </nav>
      </>
    );
  }
  
  export default Navbar;