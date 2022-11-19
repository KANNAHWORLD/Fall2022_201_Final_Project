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

        if (!email) {
            setFormError({
              ...inputError,
              email: "Enter valid email address",
            });
            return
        }

        if (!password) {
            setFormError({
              ...inputError,
              password: "Enter a valid password",
            });
            return
        }
        if (!cpassword) {
            setFormError({
              ...inputError,
              cpassword: "Confirm Password",
            });
            return
        }
        if (cpassword !== password) {
            setFormError({
            ...inputError,
              cpassword: "Passwords do not match",
            });
            return;
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