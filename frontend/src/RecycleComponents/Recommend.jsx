import axios from"axios"
import React,{useState}from"react"
import { useEffect } from "react"
import {wheather,userLoginInfo,infotomyinfo2}from"../data"
const implementsList = (img1,img2) => {
return(<><div><h3>최저 온도</h3>
<img src={img1}alt="이미지가 없습니다..."/>
</div>
<h3>최고 온도</h3>
<img src={img2}alt="이미지가 없습니다..."/>
</>)}
const Recommend = ({content,link}) => {
    const [pending,setPending] = useState(true)
    const [regist,setRegist] = useState({})
    useEffect(()=>{},[])
return(<>
<h1>{content}</h1>
{wheather.rain?<h4>외출시 우산을 챙기세요</h4>:null}
{
pending?<form onSubmit={e=>{
    (async()=>{
        e.preventDefault()
        try{
            const clothMember = new infotomyinfo2(wheather.ltemp,wheather.htemp,userLoginInfo.email)
              setRegist(await(await axios.post(link,clothMember)).data)
           setPending(false)
        }catch(e){console.log(e)}})()}}>
    <input type = "submit" value = "추천 받기"/>
</form>:
Object.keys(regist).length===1?
implementsList(regist?.clothesList[0],regist?.clothesList[1]):
alert(regist.errorMessage)}</>)}
export default React.memo(Recommend)