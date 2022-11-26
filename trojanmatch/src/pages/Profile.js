import React, { useState } from 'react';
import $ from 'jquery';
import icon from '../images/user-plus-icon.png';
// import formButton from '../images/form-button.png';
// import formButtonSelected from '../images/form-button-selected.png';
import './Profile.css';

import { useNavigate } from "react-router-dom";

function Profile() {
  let navigate = useNavigate(); 
  const routeChange = () =>{ 

    var fName = document.querySelector('input[name="first name"]').value;
    var lName = document.querySelector('input[name="last name"]').value;
    var age = document.querySelector('input[name="age"]').value;
    var gender = document.getElementById("gender").value;
    var sexuality = document.getElementById("sexuality").value;
    var media = document.querySelector('input[name="media"]').value;
    var descrip = document.getElementById("description").value

    var one = document.querySelector('input[name="one"]:checked').value;
    var two = document.querySelector('input[name="two"]:checked').value;
    var three = document.querySelector('input[name="three"]:checked').value;
    var four = document.querySelector('input[name="four"]:checked').value;
    var five = document.querySelector('input[name="five"]:checked').value;
    var six = document.querySelector('input[name="six"]:checked').value;
    var seven = document.querySelector('input[name="seven"]:checked').value;
    var eight = document.querySelector('input[name="eight"]:checked').value;
    var nine = document.querySelector('input[name="nine"]:checked').value;
    var ten = document.querySelector('input[name="ten"]:checked').value;

    let full = [fName, lName, age, gender, sexuality, media, descrip, one, two, three, four, five, six, seven, eight, nine, ten];

    let path = `../questionnaire`; 
    navigate(path,{state:full});
  }

  $(document).ready(function() {

    function fasterPreview( uploader ) {
      if ( uploader.files && uploader.files[0] ){
        // console.log("st")
            $('.profile-pic').attr('src', 
               window.URL.createObjectURL(uploader.files[0]) );
            $('.profile-pic').css("width","300px");
      }
  }

    $(".file-upload").change(function(){
      // console.log("wiw")
      fasterPreview( this );
    });


    $(".profile-pic").unbind('click').click(function(e) {
      // console.log("start")
      $(".file-upload").click();
  });

  
});
    return (
      <div className="temp">
      <form>
      <body className="Main-body">
        <div className="Title">
          Profile
        </div>

        <div>
          <img class="profile-pic" src = {icon} alt = "user icon" />
        </div>
        <input class="file-upload" type="file"/>

        <br/>

        <form className="Input">
          <label>
            First Name:
            <input type="text" name="first name" className="Box" required/>
          </label>
        </form>

        <form className="Input">
          <label>
            Last Name:
            <input type="text" name="last name" className="Box" required/>
          </label>
        </form>

        <form className="Input">
          <label>
            Age:
            <input type="number" name="age" className="Box" required/>
          </label>
        </form>

        <div className="Input">
          Gender: 
          <select name="gender" className="Select" id="gender" required>
            <option value="none" selected disabled hidden>Select an Option</option>
            <option value="male">Male</option>
            <option value="female">Female</option>
            <option value="non-binary">Non-Binary</option>
          </select>
        </div>

        <div className="Input">
          Interested In: 
          <select name="sexuality" className="Select" id="sexuality" required>
            <option value="none" selected disabled hidden>Select an Option</option>
            <option value="male">Male</option>
            <option value="female">Female</option>
            <option value="non-binary">Non-Binary</option>
            <option value="non-binary">Anyone</option>
          </select>
        </div>

        <form className="Input">
          <label>
            Social Media:
            <input type="text" name="media" className="Box"/>
          </label>
        </form>

        <form className="Input">
          <label>
            Description:
            <textarea rows="4" cols="50" id = "description" placeholder="Enter text" className="TextArea">
            
            </textarea>
          </label>
        </form>

        <br/>

        <div className="Title">
          Personality Questionnaire
        </div>

        <div className = "App-sq">
          <h4 className="InlineStart">
            How extroverted are you?
          </h4>

          <ul>
            <p>Very Introverted</p>
            <label for="extrovert" className = "radio-style">1<br />
              <input type="radio" id="one-one" name="one" value="1" checked/>
            </label>
            <label for="extrovert" className = "radio-style">2<br />
              <input type="radio" id="one-two" name="one" value="2" />
            </label>
            <label for="extrovert" className = "radio-style">3<br />
              <input type="radio" id="one-three" name="one" value="3" />
            </label>
            <label for="extrovert" className = "radio-style">4<br />
              <input type="radio" id="one-four" name="one" value="4" />
            </label>
            <label for="extrovert" className = "radio-style">5<br />
              <input type="radio" id="one-five" name="one" value="5" />
            </label>
            <p>Very Extroverted</p>
          </ul>

        </div> 

        <div className = "App-sq">
          <h4 className="InlineStart">
            How humorous are you?
          </h4>

          <ul>
            <p>Doesn't Matter</p>
            <label for="humor" className = "radio-style">1<br />
              <input type="radio" id="two-one" name="two" value="1" checked/>
            </label>
            <label for="humor" className = "radio-style">2<br />
              <input type="radio" id="two-two" name="two" value="2" />
            </label>
            <label for="humor" className = "radio-style">3<br />
              <input type="radio" id="two-three" name="two" value="3" />
            </label>
            <label for="humor" className = "radio-style">4<br />
              <input type="radio" id="two-four" name="two" value="4" />
            </label>
            <label for="humor" className = "radio-style">5<br />
              <input type="radio" id="two-five" name="two" value="5" />
            </label>
            <p>Very Important</p>
          </ul>

        </div> 

        <div className = "App-sq">
          <h4 className="InlineStart">
            How important is being adventurous to you?
          </h4>

          <ul>
            <p>Home Body</p>
            <label for="adventure" className = "radio-style">1<br />
              <input type="radio" id="three-one" name="three" value="1" checked/>
            </label>
            <label for="adventure" className = "radio-style">2<br />
              <input type="radio" id="three-two" name="three" value="2" />
            </label>
            <label for="adventure" className = "radio-style">3<br />
              <input type="radio" id="three-three" name="three" value="3" />
            </label>
            <label for="adventure" className = "radio-style">4<br />
              <input type="radio" id="three-four" name="three" value="4" />
            </label>
            <label for="adventure" className = "radio-style">5<br />
              <input type="radio" id="three-five" name="three" value="5" />
            </label>
            <p>Total Daredevil</p>
          </ul>

        </div>
        
        <div className = "App-sq">
          <h4 className="InlineStart">
            How ambitious are you?
          </h4>

          <ul>
            <p>Doesn't Matter</p>
            <label for="ambition" className = "radio-style">1<br />
              <input type="radio" id="four-one" name="four" value="1" checked/>
            </label>
            <label for="ambition" className = "radio-style">2<br />
              <input type="radio" id="four-two" name="four" value="2" />
            </label>
            <label for="ambition" className = "radio-style">3<br />
              <input type="radio" id="four-three" name="four" value="3" />
            </label>
            <label for="ambition" className = "radio-style">4<br />
              <input type="radio" id="four-four" name="four" value="4" />
            </label>
            <label for="ambition" className = "radio-style">5<br />
              <input type="radio" id="four-five" name="four" value="5" />
            </label>
            <p>Very Ambitious</p>
          </ul>

        </div>
      
        <div className = "App-sq">
          <h4 className="InlineStart">
            How artistic does your partner need to be?
          </h4>

          <ul>
            <p>Doesn't Matter</p>
            <label for="art" className = "radio-style">1<br />
              <input type="radio" id="five-one" name="five" value="1" checked/>
            </label>
            <label for="art" className = "radio-style">2<br />
              <input type="radio" id="five-two" name="five" value="2" />
            </label>
            <label for="art" className = "radio-style">3<br />
              <input type="radio" id="five-three" name="five" value="3" />
            </label>
            <label for="art" className = "radio-style">4<br />
              <input type="radio" id="five-four" name="five" value="4" />
            </label>
            <label for="art" className = "radio-style">5<br />
              <input type="radio" id="five-five" name="five" value="5" />
            </label>
            <p>Very Artsy</p>
          </ul>

        </div>
      
        <div className = "App-sq">
          <h4 className="InlineStart">
            Please rank your preferred love languages (<a href = "https://5lovelanguages.com/quizzes/love-language" target="_blank" rel="noreferrer noopener">https://5lovelanguages.com/quizzes/love-language</a>):
          </h4>

          <ul className = "App-affirmations">
            <p className = "App-language">Words of Affirmation</p>
            <label for="affirmation" className = "radio-style">1<br />
              <input type="radio" id="six-one" name="six" value="1" checked/>
            </label>
            <label for="affirmation" className = "radio-style">2<br />
              <input type="radio" id="six-two" name="six" value="2" />
            </label>
            <label for="affirmation" className = "radio-style">3<br />
              <input type="radio" id="six-three" name="six" value="3" />
            </label>
            <label for="affirmation" className = "radio-style">4<br />
              <input type="radio" id="six-four" name="six" value="4" />
            </label>
            <label for="affirmation" className = "radio-style">5<br />
              <input type="radio" id="six-five" name="six" value="5" />
            </label>
          </ul>

          <ul className = "App-touch">
            <p className = "App-language">Physical Touch</p>
            <label for="touch" className = "radio-style">1<br />
              <input type="radio" id="seven-one" name="seven" value="1" checked/>
            </label>
            <label for="touch" className = "radio-style">2<br />
              <input type="radio" id="seven-two" name="seven" value="2" />
            </label>
            <label for="touch" className = "radio-style">3<br />
              <input type="radio" id="seven-three" name="seven" value="3" />
            </label>
            <label for="touch" className = "radio-style">4<br />
              <input type="radio" id="seven-four" name="seven" value="4" />
            </label>
            <label for="touch" className = "radio-style">5<br />
              <input type="radio" id="seven-five" name="seven" value="5" />
            </label>
          </ul>

          <ul className = "App-gifts">
            <p className = "App-language">Receiving Gifts</p>
            <label for="gift" className = "radio-style">1<br />
              <input type="radio" id="eight-one" name="eight" value="1" checked/>
            </label>
            <label for="gift" className = "radio-style">2<br />
              <input type="radio" id="eight-two" name="eight" value="2" />
            </label>
            <label for="gift" className = "radio-style">3<br />
              <input type="radio" id="eight-three" name="eight" value="3" />
            </label>
            <label for="gift" className = "radio-style">4<br />
              <input type="radio" id="eight-four" name="eight" value="4" />
            </label>
            <label for="gift" className = "radio-style">5<br />
              <input type="radio" id="eight-five" name="eight" value="5" />
            </label>
          </ul>

          <ul className = "App-quality">
            <p className = "App-language">Quality Time</p>
            <label for="time" className = "radio-style">1<br />
              <input type="radio" id="nine-one" name="nine" value="1" checked/>
            </label>
            <label for="time" className = "radio-style">2<br />
              <input type="radio" id="nine-two" name="nine" value="2" />
            </label>
            <label for="time" className = "radio-style">3<br />
              <input type="radio" id="nine-three" name="nine" value="3" />
            </label>
            <label for="time" className = "radio-style">4<br />
              <input type="radio" id="nine-four" name="nine" value="4" />
            </label>
            <label for="time" className = "radio-style">5<br />
              <input type="radio" id="nine-five" name="nine" value="5" />
            </label>
          </ul>

          <ul className = "App-service">
            <p className = "App-language">Acts of Service</p>
            <label for="service" className = "radio-style">1<br />
              <input type="radio" id="ten-one" name="ten" value="1" checked/>
            </label>
            <label for="service" className = "radio-style">2<br />
              <input type="radio" id="ten-two" name="ten" value="2" />
            </label>
            <label for="service" className = "radio-style">3<br />
              <input type="radio" id="ten-three" name="ten" value="3" />
            </label>
            <label for="service" className = "radio-style">4<br />
              <input type="radio" id="ten-four" name="ten" value="4" />
            </label>
            <label for="service" className = "radio-style">5<br />
              <input type="radio" id="ten-five" name="ten" value="5" />
            </label>
          </ul>
        </div>
      </body>
      </form>
      <input type="submit" className="continue-button" value="Continue" onClick={routeChange}/>

      <br/>
      <br/>
      
    </div>
    );
  }
  
  export default Profile;