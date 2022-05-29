import {createContext} from "react";

export const InputUserData = {
    Id :"",
    Email : "",
    Nickname:""
}
export const ChangeToUserData = createContext();
export default function Userdata(props){
    const DataStruct = ()=> {if (JSON.parse(sessionStorage.getItem("login_information"))!== null){
        let user_login_data = JSON.parse(sessionStorage.getItem("login_information"));
        return(InputUserData.Id = user_login_data.userId,
            InputUserData.Email = user_login_data.email,
            InputUserData.Nickname = user_login_data.nickname)
     }} 
    DataStruct()
    
return(<ChangeToUserData.Provider value = { InputUserData}>{props.children}</ChangeToUserData.Provider>);}
