import { Link } from "react-router-dom";
import { useState,useRef } from "react";
import UserInfor from "./UserInfor";
function Home_page (){
    const input_id=useRef();
    const input_pw=useRef();
    const [login,setLogin] = useState(true);
    const [user,setUser] = useState([]);
   function outp()
   { const data = {
    userId:input_id.current.value,
    password:input_pw.current.value
  }
    const URL = `/login`;
  /*아이디&비밀번호 백엔드서버로 보내는 fetch*/ 
     fetch(URL, {
     method : "post",
     body : JSON.stringify(data),
     headers : {"Content-Type" : "application/json"},
   }).then(response=>{
    const msg_login = (response.ok) ? "Completed for Log-in :) " : "Feile to Log-in :(";
    alert(msg_login);if(response.ok===true){setLogin(false);
      /*로그인이 완료되면 백엔드 세버에서 보내주는 회원정보 받는 fetch*/
       fetch(URL)
      .then((response)=>response.json())
      .then(json=>{setUser(json.data.user);})
    }});
}

function del(){
  fetch('/', {method : "delete"}).then(()=>{setLogin(true);})
}//delete함수

return (<div>
    <h1> tlttle </h1>
    {login? <div><article>
    <input className="ID" type="text" placeholder="ID" ref={input_id} /><br />
    <input className="PW"type="password" placeholder="PASSWORD" ref={input_pw}/>
    </article><button className ="in" onClick={outp}>LOGIN</button>
    <br/>
    <button className ="another"><Link to={'/Sign_up'}>회원가입</Link></button>
    <button className ="another">ID/PW찾기</button></div>
    :
    <div> <h1>user data</h1>
    {user.map((use)=>{return<UserInfor key={use.nickname} id={use.id} text="회원정보"/>})}
    <br/>
    <button className ="in" onClick={del}>LOGOUT</button>
    </div>}
</div>);
}
export default Home_page;