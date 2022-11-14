import axios from "axios"
import { useMemo } from "react"
import { useEffect, useRef, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import { articles } from "../../data"

const Comment = () => {
    const [commentList,setCommentList] = useState([])
    const [message,setMessage] = useState({})
    const [modifr,setModifr] = useState(true)
    const [focus,setFocus] = useState(null) 
    const [modRef,creRef,creNickName,putNickName] = [useRef(),useRef(),useRef(),useRef()]
    const parms = useParams()
    const navigate = useNavigate()
    useMemo(()=>{
        (async()=>{
            try{
              setCommentList(await(await axios(`/articles/${parms.id}/comments`)).data)  
            }catch(error){console.log(error)}})()},[parms])
            useEffect(()=>{
                if(Object.keys(message).length === 2){
                    alert(message.errorMessage)
                }
                else if(typeof(message) === "string"){
                    if(!alert(message))navigate(0)}
            },[message,navigate])
return(<>
    {commentList.map((item,index)=>{return(<div key={index}>
        {index}. {item.nickname} : {item.body}
        <span onClick = {async()=>{try {
            setMessage(await(await axios.delete(`/articles/comment/${commentList[index].id}/delete`)).data)
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
                const modfContent = new articles(putNickName.current.value,modRef.current.value)
                setMessage(await(await axios.patch(`/articles/comment/${commentList[index].id}/edit`,modfContent)).data)
            }catch(error){console.log(error)}})()}}>
        <input type = "text"  placeholder="닉네임" defaultValue={commentList[index].nickname} ref = {putNickName}/><br/><br/>
        <input type = "text" ref = {modRef} defaultValue={commentList[index].body} placeholder="수정내용"/>
        <input type="submit" value = "댓글 수정"/></form></>:null}</div>)})}<hr/>


<h4>댓글 작성하기</h4>
<form onSubmit={e=>{
    (async()=>{try {
        e.preventDefault()
        const crefContent = new articles(creNickName.current.value,creRef.current.value)
        // console.log(crefContent)
        setMessage(await(await axios.post(`/articles/${parms.id}/comment/new`,crefContent)).data)
        // navigate(0)
    }catch(error){console.log(error)}})()}}>
    <input type = "text"  placeholder="닉네임" ref = {creNickName}/><br/><br/>
    <textarea placeholder="댓글을 작성해 보세요." ref = {creRef} rows="5" cols="80"/> 
    <input type = "submit" value = "작성 하기"/>
</form>
</>)}
export default Comment