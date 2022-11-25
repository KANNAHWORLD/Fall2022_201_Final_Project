import React, {useState} from "react";

export const Register=(props)=>{
    const[email, setEmail]=useState('');
    const[password,setPassword]=useState('');
    const[cpassword,setCPassword]=useState('');
    const [formError, setFormError] = useState({
        email: "",
        password: "",
        cpassword: "",
      });
    
    const handleSubmit=(e)=>{
        e.preventDefault();
        console.log(email);
        //move to form page
    }
        
    

    const validateInput=(e)=>{
        e.preventDefault();
        let inputError={
            email:"",
            password:"",
            cpassword:"",
        };

        if(!email && !password){
            setFormError({
                ...inputError,
                email: "Enter valid email address",
                password: "Enter a valid password",
              });
              return
        }

        else if (!email) {
            setFormError({
              ...inputError,
              email: "Enter valid email address",
            });
            return
        }

        else if (!password) {
            setFormError({
              ...inputError,
              password: "Enter a valid password",
            });
            return
        }
        else if (!cpassword) {
            setFormError({
              ...inputError,
              cpassword: "Confirm Password",
            });
            return
        }
        else if (cpassword !== password) {
            setFormError({
            ...inputError,
              cpassword: "Passwords do not match",
            });
            return;
        }
        else{
          //var jsonObject = {username : email};
          const axios = require('axios');
          axios({
              method: 'post',
              url: 'http://34.130.1.66:8082/OAuth',
              data: {
                UserName: email,
                Password: password
              }
            })
            .then((response) => {
              console.log(response);
              //SEND RESPONSE TO NEXT PERSON
            }, (error) => {
              //DO NOTHING???
              setFormError({
                  ...inputError,
                  password: "Invalid email or password",
                });
            });
        }
      
          setFormError(inputError);
    }
 
    return(
        <div className="auth-form-container">
                <h2>Register</h2>
            <form className="register-form" onSubmit={validateInput}>
                <label htmlFor="email">Email</label>
                <input value={email} onChange={(e)=>setEmail(e.target.value)} type="email" placeholder=" email@gmail.com" id="email" name="email"/>
                <p className="error-message">{formError.email}</p>
                <label htmlFor="password">Password</label>
                <input value={password} onChange={(e)=>setPassword(e.target.value)} type="password" placeholder=" ********" id="password" name="password"/>
                <p className="error-message">{formError.password}</p>
                <label htmlFor="cpassword">Confirm Password</label>
                <input value={cpassword} onChange={(e)=>setCPassword(e.target.value)} type="password" placeholder=" ********" id="cpassword" name="cpassword"/>
                <p className="error-message">{formError.cpassword}</p>
                <button type="submit">Create Account</button>
            </form>
            <button className="link-btn" onClick={()=>props.onFormSwitch('login')}>Already have an account? Login here!</button>
        </div>
    )
}