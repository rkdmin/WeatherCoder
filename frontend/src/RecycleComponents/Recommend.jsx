import axios from"axios"
import React,{useMemo, useState}from"react"
import { useEffect } from "react"
import {wheather,userLoginInfo}from"../data"

class ruby {
    constructor(lowTemp,highTemp,email){
        this.lowTemp = lowTemp
        this.highTemp = highTemp
        this.email = email
    }}
const Recommend = ({content,link}) => {
    const [pending,setPending] = useState(true)
    const [regist,setRegist] = useState({})
    useMemo(()=>{
        (async()=>{
            const rubyLan = new ruby(wheather.ltemp,wheather.htemp,userLoginInfo.email)
            setRegist(await(await axios.post(link,rubyLan)).data)})()},[link])
    useEffect(()=>{
        if(Object.keys(regist).length === 2 || Object.keys(regist).length === 1){
            setPending(false)}},[regist])
return(<>
<h1>{content}</h1>
{wheather.rain?<h4>외출시 우산을 챙기세요</h4>:null}
{pending?<h3>loading...</h3>
:
Object.keys(regist).length === 1 ?
<div>
<h4> 최저 온도 </h4>    
<img src = {regist.clothesList[0]} alt="이미지를 불러오는데 실패했습니다..."/>
<h4> 최고 온도 </h4>
<img src = {regist.clothesList[1]} alt="이미지를 불러오는데 실패했습니다..."/>
</div> : Object.keys(regist).length === 2 ? 
<h4>{regist.errorMessage}</h4>:
<h4> error</h4>}</>)}
export default React.memo(Recommend)