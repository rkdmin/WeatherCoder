// /*eslint-disable */
import axios from "axios"
import { useMemo } from "react"
import { useEffect, useState } from "react"
import {infotomyinfo,wheather} from "../data"
const InfoToWeather = () => {
    const [regis,setRegis] = useState({})
    const [result,setResult] = useState(true)
    useMemo(()=>{
        (async()=>{
            const nMember = new infotomyinfo(wheather.ltemp,wheather.htemp,"남성")
            setRegis(await(await axios.post('/suggest1-non-member',nMember)).data)
            setResult(false)})()},[])
            
    useEffect(()=>{
        if(Object.keys(regis).length === 2){
            alert(regis.errorMessage)
        }
        else return
    },[regis])
return(<>
 <h1>날씨 정보로 추천받기</h1>
 {wheather.rain?<h4>외출시 우산을 챙기세요</h4>:null}
 <form>
    <input type = "button" value="남성" 
    onClick={e=>{
        (async()=>{
            e.preventDefault()
            try {
                const nMember = new infotomyinfo(wheather.ltemp,wheather.htemp,e.target.value)
                setRegis(await(await axios.post('/suggest1-non-member',nMember)).data)
                setResult(false)
            } catch(error){console.log(error)}})()}}
    /><input type = "button" value="여성"
    onClick={e=>{
        (async()=>{
            e.preventDefault()
            try {
                const nMember = new infotomyinfo(wheather.ltemp,wheather.htemp,e.target.value)
                setRegis(await(await axios.post('/suggest1-non-member',nMember)).data)
                setResult(false)
            } catch(error){console.log(error)}})()}}
    />
 </form>
 <br/>
 <div>
 {result?<div>loading...</div>:
Object.keys(regis).length===2?alert(regis.errorMessage):
<><div>최저온도 <br/> <img src={regis.clothesList[0]}/></div><br/>
<div>최고온도 <br/>  <img src={regis.clothesList[1]}/></div>
</>}</div></>)}
export default InfoToWeather