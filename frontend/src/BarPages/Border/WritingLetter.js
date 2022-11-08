import axios from "axios"
import { useEffect, useRef, useState } from "react"
import { Outlet, useNavigate, useParams } from "react-router-dom"
import { articles } from "../../data"

const WritingLetter = () => {
    const parms = useParams()
    const [content,setContet] = useState({})
    const [chanArticle,setChanArticle] =useState({})
    const [modi,setModi] = useState(true)
    const [modText,modContent]  = [useRef(),useRef()] 
    const navigate= useNavigate()
    useEffect(()=>{
        (async()=>{
            try {
    setContet(await(await axios(`/articles/${parms.id}`)).data)  
            } catch(error){console.log(error)}})()},[parms])

const {title,body} = content
return(<>
{typeof(chanArticle)==="object"?alert(chanArticle.errorMessage):
typeof(chanArticle)==="string"?alert(chanArticle):null}
{modi?<>
    <h1>{title}</h1>
<h3>{body}</h3>
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
            const modContentList = new articles(modText.current.value,modContent.current.value)
            setContet(await(await axios.patch(`/articles/${parms.id}/edit`,modContentList)).data)
            setModi(true)
            navigate(0)
        } catch (error) {console.log(error)}})()}}>
    <input type="text" ref={modText} defaultValue={title}/>
    <br/>
    <textarea rows="15" ref={modContent} cols="60" defaultValue={body}/>
    <br/>
    <input type = "submit"/>
</form></>}</>)}
export default WritingLetter