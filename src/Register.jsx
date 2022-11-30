import React, {useState} from "react";
import axios from 'axios';
import {Link, useNavigate} from 'react-router-dom';

export const Register=(props)=>{
    //const navigate = useNavigate();
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
        
    
    const [post, setPost] = useState();


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
          
          var url = 'http://34.130.1.66:8082/CPE/' + email;
          const result = axios.get(url,
          {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
              // 'Access-Control-Allow-Origin' : '*',
              // 'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS',
            }
          }).then((response) => {
            var code = response.data['statusCode'];
            if(code == '404'){
              setFormError({
                ...inputError,
                cpassword: "Account already exists",
              });
            }
            else{
              setFormError({
                ...inputError,
                cpassword: "It worked",
              });
              //redirect to angela's page and send username and password info
              //navigate("/profile",{user:{userName:email,password:password}});
            }
          }, (error) => {
            //DO NOTHING???
            alert("Error");
            console.log(error);
          })
          .catch((error) => alert(error));
      
        }
         
      
          setFormError(inputError);
    }
 
    return(
        <div className="auth-form-container">
                <h2>Register</h2>
            <form className="register-form" onSubmit={validateInput}>
                <label htmlFor="email">Email</label>
                <input value={email} onChange={(e)=>setEmail(e.target.value)} type="email" placeholder=" name@gmail.com" id="email" name="email"/>
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