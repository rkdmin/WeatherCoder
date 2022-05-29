import { Link } from "react-router-dom";
import { useState,useRef } from "react";
import {ChangeToUserData} from "./Userdata";

function Home_page (){
  const input_id=useRef();
    const input_pw=useRef();
    const [login] = useState(sessionStorage.getItem("login_information"));
   function Login_Function()
   { const data = {
    userId:input_id.current.value,
    password:input_pw.current.value
  }
     fetch(`/login`, {
      method : "post", 
      body : JSON.stringify(data),
      headers : {"Content-Type" : "application/json"}
      }).then((response)=> 
     response.json()
       ).then(json=>{
         if(json.status==="Y"){
            alert("로그인 성공"); 
            window.location.reload()
            sessionStorage.setItem("login_information",JSON.stringify(json));
           }
          else if(json.status ===500){alert("로그인 실패"); }
         }); 
}
return (<div>
    <h1> tlttle </h1>
{
            login===null?<div>
            <input className="ID" type="text" placeholder="ID" ref={input_id} /><br />
            <input className="PW"type="password" placeholder="PASSWORD" ref={input_pw}/>
            <button className ="in" onClick={Login_Function}>LOGIN</button>
            <br/>
            <button className ="another"><Link to={'/Sign_up'}>회원가입</Link></button>
            <button className ="another">ID/PW찾기</button>
                </div>
        : 
              <div>
              <h1>user data</h1>
              <ChangeToUserData.Consumer>
		      {(K)=>{return <h1><Link to={`/UserInformation/${K.Id}`}>회원정보</Link></h1>}}
	            </ChangeToUserData.Consumer>
              <br/>
              <button className ="LOGOUT" onClick={()=>{fetch('/', {method : "delete"}).then(()=>{
                sessionStorage.clear();
                alert("로그아웃");
                window.location.reload()})}}>LOGOUT</button>
</div>
}
</div>);
}
export default Home_page;