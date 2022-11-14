import axios from "axios"
import { useMemo } from "react"
import { useEffect, useRef, useState } from "react"
import { Outlet, useNavigate, useParams } from "react-router-dom"
import { listText } from "../../data"

const WritingLetter = () => {
    const parms = useParams()
    const [contentOb,setContetOb] = useState({})
    const [chanArticle,setChanArticle] =useState({})
    const [modi,setModi] = useState(true)
    const [modText,modContent]  = [useRef(),useRef()] 
    const navigate= useNavigate()
    useMemo(()=>{
        (async()=>{
            try {
    setContetOb(await(await axios(`/articles/${parms.id}`)).data)  
            } catch(error){console.log(error)}})()},[parms])
            useEffect(()=>{
                if(Object.keys(chanArticle).length === 2) {
                    alert(chanArticle.errorMessage)}
                else if (typeof(chanArticle)==="string"){
                    if(!alert(chanArticle))navigate(`/border/route/0`)}},[chanArticle,navigate])
const {title,content} = contentOb

return(<>
{modi?<>
    <h1>{title}</h1>
<h3>{content}</h3>
<span onClick = {async()=>{
    setChanArticle(await(await axios.delete(`/articles/${parms.id}/delete`)).data)
    }}>삭제 하기</span>
<br/>
<span onClick = {async()=>{setModi(false)}}>수정 하기</span>
<hr/>
<h4> 댓글 </h4>
<Outlet></Outlet></>
:
<>
<h1>수정하기</h1>
<form onSubmit={e=>{
    (async()=>{
        try {
            e.preventDefault()
            const modContentList = new listText(modText.current.value,modContent.current.value)
            setChanArticle(await(await axios.patch(`/articles/${parms.id}/edit`,modContentList)).data)
            setModi(true)
            navigate(0)
        } catch (error) {console.log(error)}})()}}>
    <input type="text" ref={modText} defaultValue={title}/>
    <br/>
    <textarea rows="15" ref={modContent} cols="60" defaultValue={content}/>
    <br/>
    <input type = "submit"/>
</form></>}</>)}
export default WritingLetter