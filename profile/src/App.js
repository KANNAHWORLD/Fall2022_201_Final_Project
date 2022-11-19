import React, { useState } from 'react';
import $ from 'jquery';
import logo from './logo.png';
import icon from './user-plus-icon.png';
import formButton from './form-button.png';
import formButtonSelected from './form-button-selected.png';
import './App.css';
import './Fonts.css';

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
    <div>
      <header className="App-header">
        <img src={logo} className="logo" alt="logo" />
      </header>
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
          <select name="gender" className="Select" required>
            <option value="none" selected disabled hidden>Select an Option</option>
            <option value="male">Male</option>
            <option value="female">Female</option>
            <option value="non-binary">Non-Binary</option>
          </select>
        </div>

        <div className="Input">
          Interested In: 
          <select name="gender" className="Select" required>
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
            <input type="text" name="name" className="Box"/>
          </label>
        </form>

        <form className="Input">
          <label>
            Description:
            <textarea rows="4" cols="50" placeholder="Enter text" className="TextArea">
            
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

        <div className = "App-sq">
          <h4 className="InlineStart">
            How humorous are you?
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

        <div className = "App-sq">
          <h4 className="InlineStart">
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
        
        <div className = "App-sq">
          <h4 className="InlineStart">
            How ambitious are you?
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
      
        <div className = "App-sq">
          <h4 className="InlineStart">
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
      
        <div className = "App-sq">
          <h4 className="InlineStart">
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
      </body>
      </form>
      <input type="submit" className="continue-button" value="Continue"/>

      <br/>
      <br/>
      
    </div>
  );
}

// const Dropdown = ({ trigger, menu }) => {
//   const [open, setOpen] = React.useState(false);

//   const handleOpen = () => {
//     setOpen(!open);
//   };

//   return (
//     <div className="dropdown">
//       {React.cloneElement(trigger, {
//         onClick: handleOpen,
//       })}
//       {open ? (
//         <ul className="menu">
//           {menu.map((menuItem, index) => (
//             <li key={index} className="menu-item">
//               {React.cloneElement(menuItem, {
//                 onClick: () => {
//                   menuItem.props.onClick();
//                   setOpen(false);
//                 },
//               })}
//             </li>
//           ))}
//         </ul>
//       ) : null}
//     </div>
//   );
// };

export default App;
