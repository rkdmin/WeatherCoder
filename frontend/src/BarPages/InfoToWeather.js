/*eslint-disable */
import axios from "axios"
import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import {infotomyinfo,userLoginInfo,wheather,no1Data} from "../data"
const userImg = (...rest) => {
        const message = Object.keys(rest[0])
        return(
        <div key={rest[2]}>     
        <h4>{message[0]==="errorCode"?null:rest[1]}</h4>
        {!!rest[0]?.clothesList?!!rest[0]?.clothesList[rest[2]]?<img src={rest[0]?.clothesList[rest[2]]} 
        alt="이미지를 불러 오는데 실패 했습니다."/>:<h4>이미지가 없습니다.</h4>:
        <h4>{rest[0]?.errorMessage}</h4>}</div>)
}
const showingImg = (...rest) => {
  if(!!!sessionStorage.getItem("No1Implements")){return userImg(rest[0],rest[1],rest[2])}
 else{return userImg(rest[0],rest[1],rest[2])}
}
const keepRecommend = (img,gate) => {
        return(<>
        {["최저온도","최고온도"].map((item,index)=>showingImg(img,item,index))}
        <form onSubmit={e=>{e.preventDefault()
        async()=>{
                const nMember = new infotomyinfo(wheather.ltemp,wheather.htemp,gender)
                try{setImg(await(await axios.post(`/suggest1-non-member`,nMember)).data)}
                catch(e){console.log(e)}}}}>
        <input type = "submit" value = "다른 옷 추천"/>
        {!!userLoginInfo?null:<input type = "submit" value = "초기화" onClick={()=>{
        sessionStorage.removeItem("No1Gender")
        sessionStorage.removeItem("No1Implements")
        gate(0)}}/>}</form></>) 
}
const settingMember = async(...rest) => {
        try{
        const member = new infotomyinfo(wheather.ltemp,wheather.htemp,rest[3])
        rest[0](await(await axios.post(`/suggest1-non-member`,member)).data)
        rest[1](false)    
        sessionStorage.setItem("No1Implements",JSON.stringify(rest[2]))
}catch(e){console.log(e)}
}

const InfoToWeather = () => {
const [gender,setGender] = useState("")
const [img,setImg] = useState({})
const [regis,setRegis] = useState(true)
const gate = useNavigate()
const settingFuction = [setGender,setImg,setRegis,setGender]
useEffect(()=>{
        Object.keys(no1Data).map((item,index) =>!!sessionStorage.getItem(item)?
        settingFuction[index](no1Data[item]):null)
},[])
return(<>
        <h1>날씨 정보로 추천받기</h1>
        {wheather.rain?<h4>외출시 우산을 챙기세요</h4>:null}
        {!!sessionStorage.getItem("No1Implements")?<>{keepRecommend(img,gate)}</>:
        regis?
        sessionStorage.getItem("login_information")?
        <><form onSubmit={e=>{
                (()=>{
                       e.preventDefault()
                        try{settingMember(setImg,setRegis,img,gender)}
                        catch(e){console.log(e)}})()}}>
        <input type = "submit" value="추천 받기"/>
        </form></>
        :
        <><h4>성별</h4>
        <form onSubmit={e=>{e.preventDefault()
        async()=>{
                sessionStorage.setItem("No1Gender",gender)
                try{if(!!gender){settingMember(setImg,setRegis,img,gender)}else{alert("성별을 선택 해주세요...")}}
                catch(e){console.log(e)}}}}>   
        {["여성","남성"].map((item,index)=>{return(<div key = {index}><input type = "radio" name = "gender" value ={item} onClick={e=>setGender(e.target.value)}/>{item}<br/></div>)})}
        <input type="submit" value="추천 받기"/>
        </form></>:<>{keepRecommend(img,gate)}</>}</>)}
export default InfoToWeather