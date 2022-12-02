import axios from"axios"
import React,{useMemo, useState}from"react"
import { useRef } from "react"
import { useEffect } from "react"
import {wheather,userLoginInfo}from"../data"
import "./Recommend.moudule.css"
import mypage_btn from "../img/mypage_btn.png";
import { useNavigate } from "react-router-dom"
class ruby {
    constructor(lowTemp,highTemp,email){
        this.lowTemp = lowTemp
        this.highTemp = highTemp
        this.email = email
    }}

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

const Recommend = ({content,link}) => {
    const [pending,setPending] = useState(true)
    const [regist,setRegist] = useState({})
    const [constant,setConstant] = useState(0)
    const moveRef2 = useRef()
    const navigate = useNavigate()
    useMemo(()=>{
        (async()=>{
            const rubyLan = new ruby(wheather.ltemp,wheather.htemp,userLoginInfo.email)
            setRegist(await(await axios.post(link,rubyLan)).data)})()
        },[link])
    useEffect(()=>{
        if(Object.keys(regist).length === 2 || Object.keys(regist).length === 1){
            setPending(false)}},[regist])
return(<>
<div className="redFo">
<h3 className="header_title_content">{content}</h3>
<img src={mypage_btn} width="30px"  style={{marginTop:"12px"}} height="30px" onClick={()=>navigate('/My_page')}/>
</div>
<div className="borderHR111"/>
{pending?<h3>loading...</h3>
:
Object.keys(regist).length === 1 ?
<><div className="contanier1">
<div className="imgBox1" ref ={moveRef2}>

<div className="imgBox1Child">
<img className="imgtag1" src = {regist.clothesList[0]} alt="이미지를 불러오는데 실패했습니다..."/>
<span className="rainText123"
style={wheather.rain?{backgroundColor:"rgb(27, 92, 212)",color:"white"}:
{color:"rgb(27, 92, 212)",border:"2px solid rgb(27, 92, 212)"}}>우산 필요</span>
</div>

<div className="imgBox2Child">
<img className="imgtag1" src = {regist.clothesList[1]} alt="이미지를 불러오는데 실패했습니다..."/>
{/* <span className="rainText134"
style={wheather.rain?{backgroundColor:"rgb(27, 92, 212)",color:"white"}:
{color:"rgb(27, 92, 212)",border:"2px solid rgb(27, 92, 212)"}}>우산</span> */}
</div>

</div></div>
<input type = "button" className="left1" value="&larr;"
disabled={!constant?true:false}
style={!constant?{border:"3px solid rgb(55, 110, 211)",backgroundColor:"white",color:"rgb(55, 110, 211)"}
:
{backgroundColor:"rgb(55, 110, 211)",color:"white",border:"3px solid white"}}
onClick={()=>{
    const res = plus(constant)
    carousel(res,setConstant,moveRef2)
}}/>
<input type = "button" className="right1" value="&rarr;"
disabled={!constant?false:true}
style={constant?{border:"3px solid rgb(55, 110, 211)",backgroundColor:"white",color:"rgb(55, 110, 211)"}
:
{backgroundColor:"rgb(55, 110, 211)",color:"white",border:"3px solid white"}}
onClick={()=>{
    const res = minus(constant)
    carousel(res,setConstant,moveRef2)
}}/></> : Object.keys(regist).length === 2 ? 
<div className="errorMessage"><span>{regist.errorMessage}</span></div>:
<h4> error</h4>}
</>)}
export default React.memo(Recommend)