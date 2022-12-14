import React, {useState} from "react";
import axios from 'axios';
import {Link, useNavigate, createSearchParams} from 'react-router-dom';
//import './LogIn.css'

function SignUp(){
    const navigate = useNavigate();
    const[name, setName]=useState('');
    const[password,setPassword]=useState('');
    const[cpassword,setCPassword]=useState('');
    const [formError, setFormError] = useState({
        name: "",
        password: "",
        cpassword: "",
      });
    
    const handleSubmit=(e)=>{
        e.preventDefault();
        console.log(name);
        //move to form page
    }
        
    
    const [post, setPost] = useState();


    const validateInput=(e)=>{
        e.preventDefault();
        let inputError={
            name:"",
            password:"",
            cpassword:"",
        };

        if(!name && !password){
            setFormError({
                ...inputError,
                name: "Enter valid email address",
                password: "Enter a valid password",
              });
              return
        }

        else if (!name) {
            setFormError({
              ...inputError,
              name: "Enter valid email address",
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
          
          var url = 'http://34.130.1.66:8082/CPE/' + name;
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
              let path = `../profile`; 
              //navigate(path);
              // navigate({
              //     path,
              //     search: createSearchParams({username: name, password: password}).toString()});
              navigate(path, {state: {username: name, password: password}});

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
                <label htmlFor="name">Email</label>
                <input className = "Form-Input" value={name} onChange={(e)=>setName(e.target.value)} type="text" placeholder=" name" id="name" name="name"/>
                <p className="error-message">{formError.name}</p>
                <label htmlFor="password">Password</label>
                <input className = "Form-Input" value={password} onChange={(e)=>setPassword(e.target.value)} type="password" placeholder=" ********" id="password" name="password"/>
                <p className="error-message">{formError.password}</p>
                <label htmlFor="cpassword">Confirm Password</label>
                <input className = "Form-Input" value={cpassword} onChange={(e)=>setCPassword(e.target.value)} type="password" placeholder=" ********" id="cpassword" name="cpassword"/>
                <p className="error-message">{formError.cpassword}</p>
                <button className = "button2" type="submit">Create Account</button>
            </form>
            <Link  to={'/LogIn'} className="link-btn">Already have an account? Login here!</Link>
        </div>
    )
}

export default SignUp;