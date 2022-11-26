import logo from './logo.svg';
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import plus from './pink-plus-sign.jpeg';
import Select from 'react-dropdown-select';
import "@fontsource/comfortaa";
import formButton from './form-button.png';
import formButtonSelected from './form-button-selected.png';
import transparentOption from './transparentOption.png';
import './App.css';
import { getValue } from '@testing-library/user-event/dist/utils';

function App() {

  const [extrovertedScale, setExtrovertedScale] = useState(0);
  const [humorScale, setHumorScale] = useState(0);
  const [adventureScale, setAdventureScale] = useState(0);
  const [ambitiousScale, setAmbitiousScale] = useState(0);
  const [artisticScale, setArtisticScale] = useState(0);
  const [affirmationRank, setAffirmationRank] = useState(0);
  const [touchRank, setTouchRank] = useState(0);
  const [giftsRank, setGiftsRank] = useState(0);
  const [qualityRank, setQualityRank] = useState(0);
  const [serviceRank, setServiceRank] = useState(0);

  const [people, setPeople] = useState([]);
  const [rating, setRating] = useState([]);

  const [personOptions, setPersonOptions] = useState([]);

  //CONNECTING DROPDOWN TO BACKEND
  const [post, setPost] = useState();

  useEffect(() => {
    axios.get("http://34.130.1.66:8082/user/allprofiles").then((response) => {
     //alert(response.status);
     setPost(response.data);
    
     for (let i = 0; i < post.length; i++) {
       personOptions.push({
         label: post[i].FirstName + " " + post[i].LastName + " (@" + post[i].UserName + ")",
         value: i
       });
      }
    }).catch(error => alert(error));
  
 },[]);
  

  const ratingOptions = [
    {label: 1, value: 0},
    {label: 2, value: 1},
    {label: 3, value: 2},
    {label: 4, value: 3},
    {label: 5, value: 4},
    {label: 6, value: 5},
    {label: 7, value: 6},
    {label: 8, value: 7},
    {label: 9, value: 8},
    {label: 10, value: 9}];

  const addPerson = () => {
    setPeople([...people, {name: "", key: people.length}]);
    setRating([...rating, {rating: 0, key: rating.length}]);
  }

  const changePerson = (name, index) => {
    var array = people;
    array[index].name = name;
    setPeople(array);
  }

  const changeRating = (rate, index) => {
    var array = rating;
    array[index].rating = rate;
    setRating(array);
  }

  const submitted = (event) => {
    //alert("SUCCESSFULLY SUBMITTED WITH: " + "extroverted scale is " + extrovertedScale + " humor scale is " + humorScale + " adventure scale is now " + adventureScale + " ambitious scale is " + ambitiousScale + " artistic scale is " + artisticScale + " affirmation rank is " + affirmationRank + " touch rank is " + touchRank + " gifts rank is " + giftsRank + " quality rank is " + qualityRank + " service rank is " + serviceRank + "");
    let str = "";
    for (let i = 0; i < people.length; i++) {
      str += "person " + people[i].name + " with rating " + rating[i].rating + "\n";
    }
    alert(str);
  }

  return (

    <div className="App">
        
       
        <p>
          HEADER HERE
        </p>

         

        <h2>
          What do you look for in a partner?
        </h2>
        

        <div className = "App-sq"
        style = {
          {
            border: '2px solid pink'
          }
        }>
          <h4>
            How extroverted do you want your partner to be?
          </h4>

          <ul>
            <p>Very Introverted</p>
            <li>
              1
              <button className = "App-form-option" onClick = {() => setExtrovertedScale(1)}><img src={extrovertedScale === 1 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
      </li>
            <li>
              2
              <button className = "App-form-option" onClick = {() => setExtrovertedScale(2)}><img src={extrovertedScale === 2 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              3
              <button className = "App-form-option" onClick = {() => setExtrovertedScale(3)}><img src={extrovertedScale === 3 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              4
              <button className = "App-form-option" onClick = {() => setExtrovertedScale(4)}><img src={extrovertedScale === 4 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              5
              <button className = "App-form-option" onClick = {() => setExtrovertedScale(5)}><img src={extrovertedScale === 5 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
      </li>
            <p>Very Extroverted</p>
          </ul>

        </div> 

        <div className = "App-sq"
        style = {
          {
            border: '2px solid pink'
          }
        }>
          <h4>
            How important is a sense of humor to you?
          </h4>

          <ul>
            <p>Doesn't Matter</p>
            <li>
              1
              <button className = "App-form-option" onClick = {() => setHumorScale(1)}><img src={humorScale === 1 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              2
              <button className = "App-form-option" onClick = {() => setHumorScale(2)}><img src={humorScale === 2 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              3
              <button className = "App-form-option" onClick = {() => setHumorScale(3)}><img src={humorScale === 3 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              4
              <button className = "App-form-option" onClick = {() => setHumorScale(4)}><img src={humorScale === 4 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              5
              <button className = "App-form-option" onClick = {() => setHumorScale(5)}><img src={humorScale === 5 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <p>Very Important</p>
          </ul>

        </div> 

        <div className = "App-sq"
        style = {
          {
            border: '2px solid pink'
          }
        }>
          <h4>
            How important is being adventurous to you?
          </h4>

          <ul>
            <p>Home Body</p>
            <li>
              1
              <button className = "App-form-option" onClick = {() => setAdventureScale(1)}><img src={adventureScale === 1 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              2
              <button className = "App-form-option" onClick = {() => setAdventureScale(2)}><img src={adventureScale === 2 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              3
              <button className = "App-form-option" onClick = {() => setAdventureScale(3)}><img src={adventureScale === 3 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              4
              <button className = "App-form-option" onClick = {() => setAdventureScale(4)}><img src={adventureScale === 4 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              5
              <button className = "App-form-option" onClick = {() => setAdventureScale(5)}><img src={adventureScale === 5 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <p>Total Daredevil</p>
          </ul>

        </div>
        
        <div className = "App-sq"
        style = {
          {
            border: '2px solid pink'
          }
        }>
          <h4>
            How ambitious does your partner need to be?
          </h4>

          <ul>
            <p>Doesn't Matter</p>
            <li>
              1
              <button className = "App-form-option" onClick = {() => setAmbitiousScale(1)}><img src={ambitiousScale === 1 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              2
              <button className = "App-form-option" onClick = {() => setAmbitiousScale(2)}><img src={ambitiousScale === 2 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              3
              <button className = "App-form-option" onClick = {() => setAmbitiousScale(3)}><img src={ambitiousScale === 3 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              4
              <button className = "App-form-option" onClick = {() => setAmbitiousScale(4)}><img src={ambitiousScale === 4 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              5
              <button className = "App-form-option" onClick = {() => setAmbitiousScale(5)}><img src={ambitiousScale === 5 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <p>Very Ambitious</p>
          </ul>

        </div>
      
        <div className = "App-sq"
        style = {
          {
            border: '2px solid pink'
          }
        }>
          <h4>
            How artistic does your partner need to be?
          </h4>

          <ul>
            <p>Doesn't Matter</p>
            <li>
              1
              <button className = "App-form-option" onClick = {() => setArtisticScale(1)}><img src={artisticScale === 1 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              2
              <button className = "App-form-option" onClick = {() => setArtisticScale(2)}><img src={artisticScale === 2 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              3
              <button className = "App-form-option" onClick = {() => setArtisticScale(3)}><img src={artisticScale === 3 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              4
              <button className = "App-form-option" onClick = {() => setArtisticScale(4)}><img src={artisticScale === 4 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              5
              <button className = "App-form-option" onClick = {() => setArtisticScale(5)}><img src={artisticScale === 5 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <p>Very Artsy</p>
          </ul>

        </div>
      
        <div className = "App-sq"
        style = {
          {
            border: '2px solid pink'
          }
        }>
          <h4>
            Please rank your preferred love languages (<a href = "https://5lovelanguages.com/quizzes/love-language" target="_blank" rel="noreferrer noopener">https://5lovelanguages.com/quizzes/love-language</a>):
          </h4>

          <ul className = "App-affirmations">
            <p className = "App-language">Words of Affirmation</p>
            <li>
              1
              <button className = "App-form-option" onClick = {() => setAffirmationRank(1)}><img src={affirmationRank === 1 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              2
              <button className = "App-form-option" onClick = {() => setAffirmationRank(2)}><img src={affirmationRank === 2 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              3
              <button className = "App-form-option" onClick = {() => setAffirmationRank(3)}><img src={affirmationRank === 3 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              4
              <button className = "App-form-option" onClick = {() => setAffirmationRank(4)}><img src={affirmationRank === 4 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              5
              <button className = "App-form-option" onClick = {() => setAffirmationRank(5)}><img src={affirmationRank === 5 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
          </ul>

          <ul className = "App-touch">
            <p className = "App-language">Physical Touch</p>
            <li>
              <button className = "App-form-option" onClick = {() => setTouchRank(1)}><img src={touchRank === 1 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              <button className = "App-form-option" onClick = {() => setTouchRank(2)}><img src={touchRank === 2 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              <button className = "App-form-option" onClick = {() => setTouchRank(3)}><img src={touchRank === 3 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              <button className = "App-form-option" onClick = {() => setTouchRank(4)}><img src={touchRank === 4 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              <button className = "App-form-option" onClick = {() => setTouchRank(5)}><img src={touchRank === 5 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
          </ul>

          <ul className = "App-gifts">
            <p className = "App-language">Receiving Gifts</p>
            <li>
              <button className = "App-form-option" onClick = {() => setGiftsRank(1)}><img src={giftsRank === 1 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              <button className = "App-form-option" onClick = {() => setGiftsRank(2)}><img src={giftsRank === 2 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              <button className = "App-form-option" onClick = {() => setGiftsRank(3)}><img src={giftsRank === 3 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              <button className = "App-form-option" onClick = {() => setGiftsRank(4)}><img src={giftsRank === 4 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              <button className = "App-form-option" onClick = {() => setGiftsRank(5)}><img src={giftsRank === 5 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
          </ul>

          <ul className = "App-quality">
            <p className = "App-language">Quality Time</p>
            <li>
              <button className = "App-form-option" onClick = {() => setQualityRank(1)}><img src={qualityRank === 1 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              <button className = "App-form-option" onClick = {() => setQualityRank(2)}><img src={qualityRank === 2 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              <button className = "App-form-option" onClick = {() => setQualityRank(3)}><img src={qualityRank === 3 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              <button className = "App-form-option" onClick = {() => setQualityRank(4)}><img src={qualityRank === 4 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              <button className = "App-form-option" onClick = {() => setQualityRank(5)}><img src={qualityRank === 5 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
          </ul>

          <ul className = "App-service">
            <p className = "App-language">Acts of Service</p>
            <li>
              <button className = "App-form-option" onClick = {() => setServiceRank(1)}><img src={serviceRank === 1 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              <button className = "App-form-option" onClick = {() => setServiceRank(2)}><img src={serviceRank === 2 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              <button className = "App-form-option" onClick = {() => setServiceRank(3)}><img src={serviceRank === 3 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              <button className = "App-form-option" onClick = {() => setServiceRank(4)}><img src={serviceRank === 4 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
            <li>
              <button className = "App-form-option" onClick = {() => setServiceRank(5)}><img src={serviceRank === 5 ? formButtonSelected : formButton} className="App-logo" alt="logo" /></button>
            </li>
          </ul>

        </div>
      
        <div className = "App-sq"
        style = {
          {
            border: '2px solid pink'
          }
        }>
          <h4>Please rate any individuals you know from 1-10:</h4>
          {
            people.map((person) => {
              return (
                <div className = "App-dropdown">
                  <div className = "App-name-dropdown-div">
                    <Select options = {personOptions} onChange = {(values) => changePerson(values[0].label, person.key)} searchable = {true}/>
                  </div>
                  <Select className = "App-rating-dropdown" options = {ratingOptions} onChange = {(values) => changeRating(values[0].label, person.key)} />
                </div>
              );
            }

            )
          }
          <img className = "App-plus" src = {plus} onClick = {() => addPerson()}/>
        </div>
        
        <button className = "App-submit" onClick = {event => submitted(event)}>SUBMIT</button>

    </div>
    
    
  );

  

}


export default App;
