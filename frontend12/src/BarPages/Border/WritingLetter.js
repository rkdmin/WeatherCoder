import axios from "axios"
import { useMemo,useState } from "react"
import { Navigate, Outlet,useNavigate,useParams } from "react-router-dom"
import pageback_btn from "../../img/pageback_btn.png"
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
const navi = useNavigate()
return(<>
 <div className={"top_innercontainer"}>
        <div className={"top_arrow_div"}>
          <img
            src={pageback_btn}
            width={16}
            height={16}
            className={"Myadress_pageback"}
            onClick={()=>navi(-1)}
          />
        </div>
      </div>
<h1 className="writingTitle2">{title}</h1>
<h3 className="writingFillOut2">{content}</h3>
<br/>
<div className="borderHR132"/>
<h4 className="commentTitle"> 댓글 </h4>
<Outlet></Outlet></>)}
export default WritingLetter