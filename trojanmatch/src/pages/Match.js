import React, { useState } from 'react';
import './Match.css';
import logo from '../images/logo.png';
import '@fontsource/pacifico';
import { Link } from 'react-router-dom';
function Match() {
    const [button, setButton] = useState("ACCEPT MATCH <3");
    const changeButton = () => {
      if (button === "ACCEPT MATCH <3"){
          setButton("REJECT MATCH </3");
        }
        else{
          setButton("ACCEPT MATCH <3");
        }
    }
    const [data, setData] = useState([logo, "Melinda", "Smith", "23", "Heterosexual", "@MelindaSmith", "Hello! \nMy name is Melinda and I am looking for love <3"]);
    const type = ["Image", "First Name", "Last Name", "Age", "Sexual Orientation", "Social Media", "Description"];
    if (data.length === 0){
      return (<h1 className='nonefound'>
      Finding Your Perfect Match</h1>)
    }
    return (
       
      <div class = "whole">
          <h1 class='header1'>
            {button === "ACCEPT MATCH <3" ? 'Your Daily Match' : 'You have accepted your match!'}
              </h1>
          <img class='logomatch' src={data[0]}/>
          <div class= 'block1'>
            <p class='Title'>
              {type[1]}
            </p>
            <p class='input'>
              {data[1]}
            </p>
          </div>
          <div class= 'block1'>
            <p class='Title'>
              {type[2]}
            </p>
            <p class='input'>
              {data[2]}
            </p>
          </div>
          <div class= 'block1'>
            <p class='Title'>
              {type[3]}
            </p>
            <p class='input'>
              {data[3]}
            </p>
          </div>
          <div class= 'block1'>
            <p class='Title'>
              {type[4]}
            </p>
            <p class='input'>
              {data[4]}
            </p>
          </div>
          <div class= 'block1'>
            <p class='Title'>
              {type[5]}
            </p>
            <p class='input'>
              {data[5]}
            </p>
          </div>
          
          <div class= 'block1'>
            <p class='Title'>
              {type[6]}
            </p>
            <p class='input'>
              {data[6]}
            </p>
          </div>
          <p class='but' onClick={changeButton}>
              {button}
            
          </p>
      </div>
    );
  }
  
export default Match;