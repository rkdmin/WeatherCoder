// /*eslint-disable */
import axios from "axios"
import { useMemo, useRef } from "react"
import { useEffect, useState } from "react"
import {infotomyinfo,wheather,userLoginInfo} from "../data"
import "./InfoToWeather.moudule.css"

const minus = (num) => {
    return num-100
}
const plus = (num) => {
    return num+100
}

const carousel = (result,change,curent) => {
        if(!curent.current)return;
        curent.current.style.transform = `translate(${result}vw)`
        change(result)
}

const InfoToWeather = () => {
    const [regis,setRegis] = useState({})
    const [result,setResult] = useState(true)
    const [value,setValue] = useState("")
    const [constant,setConstant] = useState(0)
    const moveRef = useRef()
    useMemo(()=>{
        if(!userLoginInfo){
        (async()=>{
            const nMember = new infotomyinfo(wheather.ltemp,wheather.htemp,"남성")
            setRegis(await(await axios.post('/suggest1-non-member',nMember)).data)
            setResult(false)
            setValue("남성")
           })()}
        else {
            (async()=>{
                const nMember = new infotomyinfo(wheather.ltemp,wheather.htemp,userLoginInfo.gender)
                setRegis(await(await axios.post('/suggest1-non-member',nMember)).data)
                setResult(false)
               
            })()}},[])
            
    useEffect(()=>{
        if(Object.keys(regis).length === 2){
            alert(regis.errorMessage)
        }
        else return
    },[regis])
return(<>
 <h3 className="tRound">날씨 정보로 추천받기</h3>
 <div className="borderHR111"/>
 {!!userLoginInfo?
<div/>:<form className="unLoginForm"><div className="unLoginButton">
    <input type = "button" value="남성" className="unButton"
    style={value==="남성"?{backgroundColor: "#4254FF",color:"white"}:{backgroundColor: "white",color:"#4254FF"}} 
    onClick={e=>{(async()=>{
            e.preventDefault()
            try {const nMember = new infotomyinfo(wheather.ltemp,wheather.htemp,e.target.value)
                 setRegis(await(await axios.post('/suggest1-non-member',nMember)).data)
                 setResult(false)
                    setValue("남성")
                } catch(error){console.log(error)}})()}}/><input type = "button" value="여성" className="unButton"
                style={value==="여성"?{backgroundColor: "#4254FF",color:"white"}:{backgroundColor: "white",color:"#4254FF"}} 
    onClick={e=>{(async()=>{
            e.preventDefault()
            try {const nMember = new infotomyinfo(wheather.ltemp,wheather.htemp,e.target.value)
                 setRegis(await(await axios.post('/suggest1-non-member',nMember)).data)
                 setResult(false)
                 setValue("여성")
                 console.log(1)
                 } catch(error){console.log(error)}})()}}/>
                 </div>
 </form>}
 <div className="contanier">
 <div className="imgBox" ref={moveRef}>
 {result?<div>loading...</div>:
Object.keys(regis).length===2?alert(regis.errorMessage):
<><div className="imgBoxChild"><span className="no1Text">최저온도</span> 
<div className="borderHR113"/>
 <img className="img" src={regis.clothesList[0]} alt="이미지를 불러오는데 실패했습니다..."/></div>

<div className="imgBoxChild"><span className="no1Text">최고온도</span>   
<div className="borderHR113"/>
<img className="img" src={regis.clothesList[1]} alt="이미지를 불러오는데 실패했습니다..."/></div>
</>}
</div>
</div>
<button className="left" disabled={!constant?true:false} 
style={!constant?{border:"3px solid rgb(55, 110, 211)",backgroundColor:"white",color:"rgb(55, 110, 211)"}
:
{backgroundColor:"rgb(55, 110, 211)",color:"white",border:"3px solid white"}}
onClick={()=>{
   const res = plus(constant)
 carousel(res,setConstant,moveRef)
}}>&larr;</button>
<button className="right" disabled={!constant?false:true} 
style={constant?{border:"3px solid rgb(55, 110, 211)",backgroundColor:"white",color:"rgb(55, 110, 211)"}
:
{backgroundColor:"rgb(55, 110, 211)",color:"white",border:"3px solid white"}}
onClick={()=>{
const res = minus(constant)
 carousel(res,setConstant,moveRef)
}}>&rarr;</button>
<span className="rainText" 
style={wheather.rain?{backgroundColor:"rgb(27, 92, 212)",color:"white"}:
{color:"rgb(27, 92, 212)",border:"2px solid rgb(27, 92, 212)"}}>우산필요</span>
<div className="marginBox4"/>
</>)}
export default InfoToWeather