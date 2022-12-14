import React, {useState} from "react";
import axios from 'axios';
import {Link, useNavigate, createSearchParams} from 'react-router-dom';
import './LogIn.css'

function LogIn(){
    const navigate = useNavigate();
    const[email, setEmail]=useState('');
    const[password,setPassword]=useState('');
    const [formError, setFormError] = useState({
        email: "",
        password: ""
      });

    const handleSubmit=(e)=>{
        e.preventDefault();
        console.log(email);
    }
 
    const validateInput=(e)=>{
        e.preventDefault();
        let inputError={
            email:"",
            password:"",
        };

        if(!email && !password){
            setFormError({
                ...inputError,
                email: "Enter valid email address",
                password: "Enter a valid password",
              });
              return;
        }

        else if (!email) {
            setFormError({
              ...inputError,
              email: "Enter valid email address",
            });
            return;
        }

        else if (!password) {
            setFormError({
              ...inputError,
              password: "Enter a valid password",
            });
            return;
        }
        else{
            const data= {'UserName': email, 'Password': password};
            axios.post('http://34.130.1.66:8082/OAuth', data)
            .then((response) => {
                //alert(response.status);
                var code = response.data['statusCode'];
                //var prof = response.data['first'];
                //alert(prof);
                if(code == '404'){
                    setFormError({
                        ...inputError,
                        password: "Invalid email or password",
                    });
                }
                else{
                    setFormError({
                        ...inputError,
                        password: "it worked",
                    });
                    //SEND RESPONSE TO NEXT PERSON
                    let path = `../profile`; 
                    navigate(path, {state: {username: email, password: password}});

                }}).catch((error) => {
                alert(error);
            });
            // var jsonObject = {username : email};
            // const axios = require('axios');
            // axios({
            //     method: 'post',
            //     url: 'http://34.130.1.66:8082/OAuth',
            //     data: {
            //       UserName: email,
            //       Password: password
            //     }
                
            //   })
            //   .then((response) => {
            //     console.log(response);
            //     setFormError({
            //         ...inputError,
            //         password: "it worked",
            //       });
            //     //SEND RESPONSE TO NEXT PERSON
            //     //navigate("/profile",{user:{userName:email,password:password}});
            //   }, (error) => {
            //     //DO NOTHING???
            //     setFormError({
            //         ...inputError,
            //         password: "Invalid email or password",
            //       });
            //   });
        }
      
          setFormError(inputError);
    }
 
    return(
        <div className="auth-form-container">
            <h2>Login to Your Account</h2>
            <form className="login-form" onSubmit={validateInput}>
                <label className="label2" htmlFor="email">Email</label>
                <input className = "Form-Input" value={email} onChange={(e)=>setEmail(e.target.value)} type="email" placeholder=" email@gmail.com" id="email" name="email"/>
                <p className="error-message">{formError.email}</p>
                <label className="label2" htmlFor="password">Password</label>
                <input className = "Form-Input" value={password} onChange={(e)=>setPassword(e.target.value)} type="password" placeholder=" ********" id="password" name="password"/>
                <p className="error-message">{formError.password}</p>
                <button className = "button2" type="submit">Login</button>
            </form>
            <Link to={'/SignUp'} className="link-btn" >Don't have an account? Register here!</Link>
        </div>
    )
}
export default LogIn;