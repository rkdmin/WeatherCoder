import axios from "axios"
import { useMemo } from "react"
import { useState } from "react"
import { Outlet,useParams } from "react-router-dom"
import "./WrittingLetter.moudule.css"
const WritingLetter = () => {
    const parms = useParams()
    const [contentOb,setContetOb] = useState({})
  
    useMemo(()=>{
        (async()=>{
            try {
    setContetOb(await(await axios(`/articles/${parms.id}`)).data)  
            } catch(error){console.log(error)}})()},[parms])
const {title,content} = contentOb

return(<>

<h1 className="writingTitle2">{title}</h1>
<h3 className="writingFillOut2">{content}</h3>
<br/>
<div className="borderHR132"/>
<h4 className="commentTitle"> 댓글 </h4>
<Outlet></Outlet></>)}
export default WritingLetter