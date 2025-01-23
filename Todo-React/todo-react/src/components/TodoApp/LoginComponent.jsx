import { useState } from "react"
import { useNavigate } from "react-router-dom"
function LoginComponent() {
    const [username,setUsername]=useState('in28minutes')
    const [password,setPassword]=useState('')
    const [showSuccessMessage,setShowSuccessMessage]=useState(false)
    const [showErrorMessage,setShowErrorMessage]=useState(false)

    const navigate=useNavigate();

    const handleUsernameChange=(e)=>{
          setUsername(e.target.value)
    }
    const handlePasswordChange=(e)=>{
        setPassword(e.target.value)
  }
  const handleSubmit=()=>{
    if(username==='in28minutes' && password==='dummy'){
        setShowSuccessMessage(true)
        setShowErrorMessage(false)
        navigate(`/welcome/${username}`)
    }
    else{
        setShowSuccessMessage(false)
        setShowErrorMessage(true)
        navigate('/login')
    }
  }
 
    return (
        <div className="Login">
            <h1>Time to Login</h1>
            {showSuccessMessage && <div className='successMessage'>Authentication Successful</div>}
            {showErrorMessage &&  <div className='errorMessage'>Authentication Failed Please check your credentials</div> }
            <div className="LoginForm">
                <div>
                    <label >UserName</label>
                    <input type="text" name="username" id="username" value={username}onChange={handleUsernameChange}/>
                </div>
                <div>
                    <label>Password</label>
                    <input type="password" name="password" id="password" value={password} onChange={handlePasswordChange} />
                </div>
                <div>
                    <button type="button" name="login" onClick={handleSubmit}>Login</button>
                </div>
            </div>
        </div>
    );
}


export default LoginComponent;