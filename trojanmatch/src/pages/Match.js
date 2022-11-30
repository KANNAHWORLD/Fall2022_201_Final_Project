import React, { useState, useEffect } from 'react';
import './Match.css';
import logo from '../images/logo.png';
import '@fontsource/pacifico';
import { Link } from 'react-router-dom';
import http from "./Common.js"
import axios from 'axios';
import { useLocation } from "react-router-dom";

function Match(){
  const {state} = useLocation();
  React.useEffect(() => {
    var user = state.username;
    http.get("/matches/" + user).then(res => {
      setData(res.data);
    }).then(res => {
      if (data.length === 0){
        
      }
      else{
        fetchImage('http://34.130.1.66:8082/user/getImg/' + data.UserName);
      }
    }
      
    );




  }, []);

  const getFile = async (nombreArchivo) => { 
    const tokenApp = window.localStorage.getItem('token')
    const {data: res} = await axios.get(`http://34.130.1.66:8082/user/getImg/sanjana`,
    {  headers: { Authorization: `${tokenApp}` },responseType: 'blob',});
    return res;};
    const [button, setButton] = useState("REJECT MATCH <3");
    const changeButton = () => {
      if (button === "ACCEPT MATCH <3"){
          setButton("REJECT MATCH </3");
        }
        else{
          setButton("ACCEPT MATCH <3");
        }
    }
    const [imageprof, setImageProf] = useState(null);
    const fetchImage = async (url) => {
      try {
        const response = await fetch(url);
        const imageBytes = await response.arrayBuffer();
        var blob = new Blob([imageBytes], { type: "image/jpeg" });
        var imageUrl = URL.createObjectURL(blob);
        setImageProf(imageUrl);
      } catch (error) {
        console.log("ERROR:", error);
      }
    };
    
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
          <img class='logomatch' src={imageprof}/>
          <div class= 'block1'>
            <p class='Title1'>
              {type[1]}
            </p>
            <p class='input1'>
              {data.first}
            </p>
          </div>
          <div class= 'block1'>
            <p class='Title1'>
              {type[2]}
            </p>
            <p class='input1'>
              {data.last}
            </p>
          </div>
          <div class= 'block1'>
            <p class='Title1'>
              {type[3]}
            </p>
            <p class='input1'>
              {data.age}
            </p>
          </div>
          <div class= 'block1'>
            <p class='Title1'>
              {type[4]}
            </p>
            <p class='input1'>
              {data.SexOrient === 1 ? "Male" : ( data.SexOrient === 2 ? "Female" : "Anyone")}
            </p>
          </div>
          <div class= 'block1'>
            <p class='Title1'>
              {type[5]}
            </p>
            <p class='input1'>
              {data.insta}
            </p>
          </div>
          
          <div class= 'block1'>
            <p class='Title1'>
              {type[6]}
            </p>
            <p class='input1'>
              {data.description}
            </p>
          </div>
          <p class='but' onClick={()=>{
            changeButton(); 
            axios({
              method: 'post',
              url: 'http://http://34.130.1.66:8082/user/changeMatch',
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