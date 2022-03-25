import {useRef} from 'react';

function Sign_up (){
  const input_id=useRef();
  const input_pw=useRef();
  const input_em=useRef();
  const input_nm=useRef();
  function Loation(){
  const able =
  { userId:input_id.current.value,
    password:input_pw.current.value,
    email:input_em.current.value,
    nickname:input_nm.current.value,
    level:0,cert:0,status:"Y",regDate:null
  }
   const URL = `/join`;
   fetch(URL, {
     method : "post",
     body : JSON.stringify(able),
     headers : {"Content-Type" : "application/json"},
   }).then(response=>{
   const msg = (response.ok) ? "Completed for Sign Up :) " : "Feile to Sign Up :(";
   alert(msg);
   if(response.ok === true){ window.location.href = '/'} 
  }
   ); }


return(<div>
  <h1>회원가입</h1>
      <input type="tetx" placeholder="ID" ref={input_id} />
      <br />
      <input type="password" placeholder="PASSWORE" ref={input_pw}/> 
      <br />
      <input type="tetx" placeholder="E-MAIL" ref={input_em} />
      <br />
      <input type="tetx" placeholder="NICKNAME" ref={input_nm}/>
      <input type="button" value="가입" onClick={Loation}/>
      <br />
      <input type="button" value="홈페이지" onClick={()=>{window.location.href = '/'}}/>
  </div>);
}

export default Sign_up;