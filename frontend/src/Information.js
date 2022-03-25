import {useState,useEffect} from "react";
function Information (){
    const [infoma,setInfoma] = useState([]);
    useEffect(()=>{
        fetch('/')
        .then((response)=>response.json())
        .then(json=>{setInfoma(json.data.infoma);})
      },[])
    return (<div> 
         <h1>User Information</h1>
     {infoma.map((user_data)=>{
       return(<div>
    <h3>Id : {user_data.userId}</h3>
    <h3>Email : {user_data.email}</h3>
    <h3>NickName : {user_data.nickname}</h3>
    <h3>RegDate : {user_data.regDate}</h3>
    </div>);})}
    </div>);
}
export default Information;