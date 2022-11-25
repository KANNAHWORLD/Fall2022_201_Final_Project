import React, {useState} from "react";
export const Login=(props)=>{
    const[email, setEmail]=useState('');
    const[password,setPassword]=useState('');

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
            <h2>Login to Your Account</h2>
            <form className="login-form" onSubmit={validateInput}>
                <label htmlFor="email">Email</label>
                <input value={email} onChange={(e)=>setEmail(e.target.value)} type="email" placeholder=" email@gmail.com" id="email" name="email"/>
                <label htmlFor="password">Password</label>
                <input value={password} onChange={(e)=>setPassword(e.target.value)} type="password" placeholder=" ********" id="password" name="password"/>
                <button type="submit">Login</button>
            </form>
            <button className="link-btn"onClick={()=>props.onFormSwitch('register')}>Don't have an account? Register here!</button>
        </div>
    )
}