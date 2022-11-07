import axios from "axios"
import { useEffect, useRef, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import { content } from "../../data"

const Comment = () => {
    const [commentList,setCommentList] = useState([])
    const [message,setMessage] = useState(null)
    const [modifr,setModifr] = useState(true)
    const [focus,setFocus] = useState(null) 
    const [modRef,creRef] = [useRef(),useRef()]
    const parms = useParams()
    const navigate = useNavigate()
    useEffect(()=>{
        (async()=>{
            try{
              setCommentList(await(await axios(`/articles/${parms.id}/comments`)).data)  
            }catch(error){console.log(error)}})()},[parms])
return(<>
{typeof(message)==="object"?alert(message.errorMessage):typeof(message)==="string"?alert(message):null}
    {commentList.map((item,index)=>{return(<div>
        {index}. {item.nickname} : {item.body}
        <span onClick = {async()=>{try {
            await axios.delete(`/articles/comment/${commentList.articleId}/delete`)
            navigate(0)
        } catch (error) {console.log(error)}
            }}>삭제하기</span>
        <span onClick={()=>{
            setModifr(!modifr)
            setFocus(index)}}>수정하기</span>
        {modifr?null:focus===index?
        <><br/>
        <form onSubmit={e=>{
            (async()=>{try {
                e.preventDefault()
                const modfContent = new content(item.nickname,modRef.current.value)
                setMessage(await(await axios.patch(`/articles/comment/${commentList.articleId}/edit`,modfContent)).data)
                navigate(0)
            }catch(error){console.log(error)}})()}}>
        <input type = "text" ref = {modRef} placeholder="수정내용"/>
        <input type="submit" value = "댓글 수정"/></form></>:null}</div>)})}<hr/>
<h4>댓글 작성하기</h4>
<form onSubmit={e=>{
    (async()=>{try {
        e.preventDefault()
        const creContent = new content(Date.now(),creRef.current.value)
        setMessage(await(await axios.post(`articles/${parms.id}/new`,creContent)).data)
        navigate(0)
    }catch(error){console.log(error)}})()}}>
    <textarea placeholder="댓글을 작성해 보세요." ref = {creRef} rows="5" cols="80"/> 
    <input type = "submit" value = "작성 하기"/>
</form>
</>)}
export default Comment