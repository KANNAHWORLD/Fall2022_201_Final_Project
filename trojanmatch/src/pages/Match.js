import React, { useState, useEffect } from 'react';
import './Match.css';
import logo from '../images/logo.png';
import '@fontsource/pacifico';
import { Link } from 'react-router-dom';
import http from "./Common.js"
import axios from 'axios';
function Match(){
  React.useEffect(() => {
    http.get("/matches/sanjana123").then(res => {
      setData(res.data);
    });

  }, []);
    const [button, setButton] = useState("REJECT MATCH <3");
    const changeButton = () => {
      if (button === "ACCEPT MATCH <3"){
          setButton("REJECT MATCH </3");
        }
        else{
          setButton("ACCEPT MATCH <3");
        }
    }
    const [data, setData] = useState([logo, "Melinda", "Smith", "23", "Heterosexual", "@MelindaSmith", "Hello! \nMy name is Melinda and I am looking for love <3"]);
    const type = ["Image", "First Name", "Last Name", "Age", "Interested in", "Social Media", "Description"];
    if (data.length === 0){
      return (<h1 className='nonefound'>
      Finding Your Perfect Match</h1>)
    }
    return (
       
      <div class = "whole">
          <h1 class='header1'>
            {button === "ACCEPT MATCH <3" ? 'You have rejected your match' : 'Your Daily Match'}
              </h1>
          <img class='logomatch' src={logo}/>
          <div class= 'block1'>
            <p class='Title'>
              {type[1]}
            </p>
            <p class='input'>
              {data.first}
            </p>
          </div>
          <div class= 'block1'>
            <p class='Title'>
              {type[2]}
            </p>
            <p class='input'>
              {data.last}
            </p>
          </div>
          <div class= 'block1'>
            <p class='Title'>
              {type[3]}
            </p>
            <p class='input'>
              {data.age}
            </p>
          </div>
          <div class= 'block1'>
            <p class='Title'>
              {type[4]}
            </p>
            <p class='input'>
              {data.SexOrient === 1 ? "Male" : ( data.SexOrient === 2 ? "Female" : "Anyone")}
            </p>
          </div>
          <div class= 'block1'>
            <p class='Title'>
              {type[5]}
            </p>
            <p class='input'>
              {data.insta}
            </p>
          </div>
          
          <div class= 'block1'>
            <p class='Title'>
              {type[6]}
            </p>
            <p class='input'>
              {data.description}
            </p>
          </div>
          <p class='but' onClick={()=>{
            changeButton(); 
            axios({
              method: 'post',
              url: 'http://localhost:8082/user/changeMatch',
              data: {
                user: "sanjana123",
                match: data.UserName
              }
            })
            .then((response) => {
              console.log(response);
              //SEND RESPONSE TO NEXT PERSON
            })
            }}>
              {button}
            
          </p>
      </div>
    );
  }
  
export default Match;