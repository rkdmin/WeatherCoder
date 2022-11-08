import axios from "axios"
import React,{useRef} from "react"
import { useState } from "react"
import { useEffect } from "react"
import { Link, useNavigate } from "react-router-dom"

class login {
  constructor(email,password){
  this.email = email
  this.password = password
  }
}

const Login_page = () => {
  const [result,setResult] = useState({})
  const input_id=useRef()
  const input_pw=useRef()
  const navigate = useNavigate()
  useEffect(()=>{if(Object.keys(result).length===2)
    {alert(result.errorMessage)}
    else if (Object.keys(result).length===7){
    sessionStorage.setItem("login_information",JSON.stringify(result))
    window.history.back()
    navigate(0)
    }
    else return;
  },[result,navigate])
return (<>
    <h1> Log-in </h1>
            <form onSubmit={e=>{
              (async()=>{
                e.preventDefault()
                try {
                  const user = new login(input_id.current.value,input_pw.current.value)
                  setResult(await(await axios.post(`/login`,user)).data)
                } catch (error) {console.log(error)}})()}}>
            <input className="ID" type="text" placeholder="아이디를 입력하세요." ref={input_id} /><br />
            <input className="PW"type="password" placeholder="비밀번호를 입력하세요." ref={input_pw}/>
              <input type="submit" className ="in" value="LOGIN"/><br/><br/>
            </form><Link to={'/Id_find'}>아이디</Link> 찾기</>)}
export default Login_page